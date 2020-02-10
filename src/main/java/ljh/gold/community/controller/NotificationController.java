package ljh.gold.community.controller;

import ljh.gold.community.dto.NotificationDTO;
import ljh.gold.community.dto.PaginationDOT;
import ljh.gold.community.enums.NotificationTypeEnum;
import ljh.gold.community.model.User;
import ljh.gold.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/10 13:48
 */
@Controller
public class NotificationController {
    @Autowired(required = false)
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType() ||
                NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" +notificationDTO.getOuterId();
        }
        return "redirect:/";


    }
}
