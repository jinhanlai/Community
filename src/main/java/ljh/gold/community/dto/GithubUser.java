package ljh.gold.community.dto;


import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Integer id;
    private String bio;
    private String login;
    private String avatar_url;
}
