package ljh.gold.community.controller;

import ljh.gold.community.dto.AccessTokenDTO;
import ljh.gold.community.dto.GithubUser;
import ljh.gold.community.mapper.UserMapper;
import ljh.gold.community.model.User;
import ljh.gold.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;


    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser.getId()!=0){
            //登录成功
            User user = new User();
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }

}
