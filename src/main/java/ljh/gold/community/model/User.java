package ljh.gold.community.model;


public class User {
    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public void setGmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Integer getId() {
        return id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public Long getGmt_modified() {
        return gmt_modified;
    }


}
