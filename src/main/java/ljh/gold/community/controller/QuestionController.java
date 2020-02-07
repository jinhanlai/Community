package ljh.gold.community.controller;


import ljh.gold.community.dto.CommentDTO;
import ljh.gold.community.dto.QuestionDTO;
import ljh.gold.community.service.CommentService;
import ljh.gold.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        //按question的id查询数据库并拼接user
        QuestionDTO questionDTO=questionService.getById(id);
        List<CommentDTO> comments= commentService.listByQuestionId(id);

        //增加阅读数
        questionService.increaseView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
