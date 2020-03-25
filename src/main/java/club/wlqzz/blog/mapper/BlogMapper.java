package club.wlqzz.blog.mapper;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.Comments;
import club.wlqzz.blog.pojo.Reply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper {
    @Select("select * from t_blog")
    List<Blog> selectAll() throws Exception;

    @Select("select * from t_blog where title=#{title}")
    Blog selectByTitle(String title) throws Exception;

    @Select("select * from t_blog where user_id=#{userId}")
    List<Blog> selectAllUser(Integer user_id) throws Exception;

    @Select("select * from t_blog where id=#{id}")
    Blog selectById(Integer id) throws Exception;

    @Select("select * from t_blog order by date desc limit 5")
    List<Blog> selectBlogLatest() throws Exception;

    @Select("select * from t_comments where blog_id=#{blogId}")
    List<Comments> selectComments(Integer id) throws Exception;

    @Select("select * from t_reply where blog_id=#{blogId}")
    Reply selectReply(Integer id)throws Exception;

    @Select("select * from t_blog where title like concat('%',#{title},'%')")
    List<Blog>selectBlogs(String title)throws Exception;

    @Insert("insert into t_blog(title,context,user_id) values(#{title},#{context},#{userId})")
    void insert(Blog blog) throws Exception;

    @Insert("insert into t_comments(context,user_id,blog_id) values(#{context},#{userId},#{blogId})")
    void insertComment(Comments comments)throws Exception;

    @Delete("delete from t_blog where id=#{id}")
    void delete(Integer id) throws Exception;

    @Update("update t_blog set title=#{title},context=#{context},count=#{count} where id=#{id}")
    void update(Blog blog) throws Exception;

}
