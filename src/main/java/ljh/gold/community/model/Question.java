package ljh.gold.community.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.id
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.title
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.gmt_create
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private Long gmt_create;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.gmt_modified
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private Long gmt_modified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.creator
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.comment_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private Integer comment_count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.view_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private Integer view_count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.like_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private Integer like_count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.tag
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.description
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.id
     *
     * @return the value of question.id
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.id
     *
     * @param id the value for question.id
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.title
     *
     * @return the value of question.title
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.title
     *
     * @param title the value for question.title
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.gmt_create
     *
     * @return the value of question.gmt_create
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public Long getGmt_create() {
        return gmt_create;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.gmt_create
     *
     * @param gmt_create the value for question.gmt_create
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.gmt_modified
     *
     * @return the value of question.gmt_modified
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public Long getGmt_modified() {
        return gmt_modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.gmt_modified
     *
     * @param gmt_modified the value for question.gmt_modified
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setGmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.creator
     *
     * @return the value of question.creator
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.creator
     *
     * @param creator the value for question.creator
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.comment_count
     *
     * @return the value of question.comment_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public Integer getComment_count() {
        return comment_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.comment_count
     *
     * @param comment_count the value for question.comment_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.view_count
     *
     * @return the value of question.view_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public Integer getView_count() {
        return view_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.view_count
     *
     * @param view_count the value for question.view_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.like_count
     *
     * @return the value of question.like_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public Integer getLike_count() {
        return like_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.like_count
     *
     * @param like_count the value for question.like_count
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.tag
     *
     * @return the value of question.tag
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.tag
     *
     * @param tag the value for question.tag
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.description
     *
     * @return the value of question.description
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.description
     *
     * @param description the value for question.description
     *
     * @mbg.generated Wed Feb 05 17:22:04 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}