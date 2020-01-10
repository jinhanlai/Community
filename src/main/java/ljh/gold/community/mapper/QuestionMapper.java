package ljh.gold.community.mapper;

import ljh.gold.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void insert(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{account_id} limit #{offset},#{size}")
    List<Question> listByCreator(@Param("account_id")Integer account_id, @Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select count(1) from question where creator=#{account_id}")
    Integer countByCreator(@Param("account_id") Integer account_id);
}

