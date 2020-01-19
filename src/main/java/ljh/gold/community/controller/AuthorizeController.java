package ljh.gold.community.controller;

import ljh.gold.community.dto.AccessTokenDTO;
import ljh.gold.community.dto.GithubUser;
import ljh.gold.community.mapper.UserMapper;
import ljh.gold.community.model.User;
import ljh.gold.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class AuthorizeController {

    private final GithubProvider githubProvider;
    private final UserMapper userMapper;

    @Value("${github.client.id}")
    private String  clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    public AuthorizeController(GithubProvider githubProvider, UserMapper userMapper) {
        this.githubProvider = githubProvider;
        this.userMapper = userMapper;
    }

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
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
            user.setAvatar_url(githubUser.getAvatar_url());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            if(userMapper.findByAccount_id(user.getAccount_id())!=null){
                userMapper.update(user);
            }
            else{
                userMapper.insert(user);
            }
            response.addCookie(new Cookie("token",token));
        }else {
            //登录失败
        }
        return "redirect:/";
    }

}
