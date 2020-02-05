package ljh.gold.community.controller;

import ljh.gold.community.dto.CommentDTO;
import ljh.gold.community.dto.ResultDTO;
import ljh.gold.community.exception.CustomizeErrorCode;
import ljh.gold.community.mapper.CommentMapper;
import ljh.gold.community.model.Comment;
import ljh.gold.community.model.User;
import ljh.gold.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping(value = "/comment" ,method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                        HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorof(CustomizeErrorCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParent_id(commentDTO.getParent_id());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setCommentator(user.getAccount_id());
        comment.setLike_count(0L);

        commentService.insert(comment);

        return ResultDTO.okof();
    }
}
