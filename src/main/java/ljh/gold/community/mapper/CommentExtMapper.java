package ljh.gold.community.mapper;

import ljh.gold.community.model.Comment;
import ljh.gold.community.model.CommentExample;
import ljh.gold.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {

    int increaseCommentCount(Comment comment);
}