package ljh.gold.community.controller;


import ljh.gold.community.cache.TagCache;
import ljh.gold.community.dto.QuestionDTO;
import ljh.gold.community.dto.TagDTO;
import ljh.gold.community.mapper.QuestionMapper;
import ljh.gold.community.model.Question;
import ljh.gold.community.model.User;
import ljh.gold.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        List<TagDTO> tagDTOS = TagCache.get();
        model.addAttribute("tags", tagDTOS);
        return "publish";
    }


    @GetMapping("/publish")
    public String publish(HttpServletRequest request, Model model) {

        List<TagDTO> tagDTOS = TagCache.get();
        model.addAttribute("tags", tagDTOS);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        return "publish";
    }

    @PostMapping("/publish")
    public String doPostPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model) {
        List<TagDTO> tagDTOS = TagCache.get();
        model.addAttribute("tags", tagDTOS);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "redirect:/";
        }
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        String invalid=TagCache.invilid(tag);
        if(!StringUtils.isBlank(invalid)){
            model.addAttribute("error", "输入非法标签"+invalid);
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getAccount_id());
        question.setId(id);
        questionService.createOrUpdate(question);



        return "redirect:/";
    }
}
