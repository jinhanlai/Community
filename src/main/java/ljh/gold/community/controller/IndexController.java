package ljh.gold.community.controller;

import ljh.gold.community.dto.PaginationDOT;
import ljh.gold.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private final QuestionService questionService;

    public IndexController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "3")Integer size,
                        @RequestParam(name = "search",required = false)String search) {
        PaginationDOT pagination=questionService.list(search,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        return "index";
    }
}
