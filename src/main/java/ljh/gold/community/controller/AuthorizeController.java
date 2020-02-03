package ljh.gold.community.controller;

import ljh.gold.community.dto.AccessTokenDTO;
import ljh.gold.community.dto.GithubUser;
import ljh.gold.community.model.User;
import ljh.gold.community.provider.GithubProvider;
import ljh.gold.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private  GithubProvider githubProvider;
    @Autowired(required = false)
    private UserService userService;

    @Value("${github.client.id}")
    private String  clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null&&githubUser.getId()!=0){
            //登录成功
            User user = new User();
            user.setAccount_id(githubUser.getId());
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
            user.setAvatar_url(githubUser.getAvatar_url());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
        }else {
            //登录失败
             log.error("callback get github error,{}", githubUser);
            return "redirect:/";
        }
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String  logout(HttpServletRequest request,
                          HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
