package ljh.gold.community.controller;


import ljh.gold.community.dto.QuestionDTO;
import ljh.gold.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        //按question的id查询数据库并拼接user
        QuestionDTO questionDTO=questionService.getById(id);
        //增加阅读数
        questionService.increaseView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
