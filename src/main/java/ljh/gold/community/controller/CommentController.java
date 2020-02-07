package ljh.gold.community.controller;

import ljh.gold.community.dto.CommentCreateDTO;
import ljh.gold.community.dto.CommentDTO;
import ljh.gold.community.dto.ResultDTO;
import ljh.gold.community.enums.CommentTypeEnum;
import ljh.gold.community.exception.CustomizeErrorCode;
import ljh.gold.community.mapper.CommentMapper;
import ljh.gold.community.model.Comment;
import ljh.gold.community.model.User;
import ljh.gold.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/5 11:42
 */
@Controller
public class CommentController {
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorof(CustomizeErrorCode.NOT_LOGIN);
        }
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorof(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParent_id(commentCreateDTO.getParent_id());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setCommentator(user.getAccount_id());
        comment.setLike_count(0L);
        commentService.insert(comment);
        return ResultDTO.okof();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okof(commentDTOS);
    }
}
