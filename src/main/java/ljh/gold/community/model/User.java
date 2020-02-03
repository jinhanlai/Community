package ljh.gold.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private Integer account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String bio;
    private String avatar_url;


}
