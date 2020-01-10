package ljh.gold.community.controller;

import ljh.gold.community.dto.PaginationDOT;
import ljh.gold.community.model.User;
import ljh.gold.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    private final QuestionService questionService;

    public ProfileController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "3")Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
        }
        PaginationDOT paginationDOT=questionService.listByCreator(user.getAccount_id(),page,size);
        model.addAttribute("pagination",paginationDOT);
        return "profile";
    }
}
