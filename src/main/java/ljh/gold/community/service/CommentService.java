package ljh.gold.community.service;

import ljh.gold.community.dto.CommentDTO;
import ljh.gold.community.enums.CommentTypeEnum;
import ljh.gold.community.enums.NotificationStatusEnum;
import ljh.gold.community.enums.NotificationTypeEnum;
import ljh.gold.community.exception.CustomizeErrorCode;
import ljh.gold.community.exception.CustomizeException;
import ljh.gold.community.mapper.*;
import ljh.gold.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/5 15:43
 */
@Service
public class CommentService {
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private QuestionExtMapper questionExtMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private CommentExtMapper commentExtMapper;
    @Autowired(required = false)
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment, User commentator) {
        if (comment.getParent_id() == null || comment.getParent_id() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParent_id());//回复的评论
            if (dbcomment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.selectByPrimaryKey(dbcomment.getParent_id());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            commentMapper.insertSelective(comment);
            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParent_id());
            parentComment.setComment_count(1);
            commentExtMapper.increaseCommentCount(parentComment);
            //创建通知
            createNotify(comment, dbcomment.getCommentator(),question.getId(), NotificationTypeEnum.REPLY_COMMENT, commentator.getName(), question.getTitle());
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParent_id());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setComment_count(1);
            questionExtMapper.increaseCommentCount(question);
            //创建通知
            createNotify(comment, question.getCreator(),question.getId(), NotificationTypeEnum.REPLY_QUESTION, commentator.getName(), question.getTitle());
        }
    }

    private void createNotify(Comment comment, Long receiver,Long outerId, NotificationTypeEnum notificationType, String notifierName, String outerTitle) {
        if (receiver.equals(comment.getCommentator())){
            return;
        }
        Notification notification = new Notification();
        notification.setGmt_create(System.currentTimeMillis());
        notification.setNotifier(comment.getCommentator());//当前回复评论人
        notification.setReceiver(receiver);//提出问题的人
        notification.setOuterId(outerId);//问题的id
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setType(notificationType.getType());//获取回复类型
        notification.setNotifier_name(notifierName);
        notification.setOuter_title(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample example = new CommentExample();
        example.createCriteria().andParent_idEqualTo(id).andTypeEqualTo(type.getType());
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //获取去重的评论人（set不允许出现重复的值）
        List<Long> userIds = comments.stream().map(Comment::getCommentator).distinct().collect(Collectors.toList());

        //获取评论人并转换成Map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccount_idIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getAccount_id, user -> user));

        //转换comment为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;

    }
}
