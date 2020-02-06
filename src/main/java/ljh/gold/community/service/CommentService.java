package ljh.gold.community.service;

import ljh.gold.community.enums.CommentTypeEnum;
import ljh.gold.community.exception.CustomizeErrorCode;
import ljh.gold.community.exception.CustomizeException;
import ljh.gold.community.mapper.CommentMapper;
import ljh.gold.community.mapper.QuestionExtMapper;
import ljh.gold.community.mapper.QuestionMapper;
import ljh.gold.community.model.Comment;
import ljh.gold.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParent_id() == null || comment.getParent_id() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParent_id());
            if (dbcomment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParent_id());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setComment_count(1);
            questionExtMapper.increaseCommentCount(question);
        }
    }
}
