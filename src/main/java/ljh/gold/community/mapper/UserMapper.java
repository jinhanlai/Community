package ljh.gold.community.mapper;

import ljh.gold.community.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where account_id=#{account_id}")
    User findByAccount_id(@Param("account_id") Integer account_id);

    @Update("update user set name=#{name},token=#{token},gmt_create=#{gmt_create},gmt_modified=#{gmt_modified},bio=#{bio},avatar_url=#{avatar_url} where account_id=#{account_id}")
    void update(User user);
}
