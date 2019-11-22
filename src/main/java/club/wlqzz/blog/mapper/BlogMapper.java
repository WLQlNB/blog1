package club.wlqzz.blog.mapper;

import club.wlqzz.blog.pojo.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogMapper {
    @Select("select * from t_blog")
    List<Blog> selectAll() throws Exception;

    @Select("select * from t_blog where user_id=#{userId}")
    List<Blog> selectAllUser(Integer user_id) throws Exception;

    @Select("select * from t_blog where id=#{id}")
    Blog selectById(Integer id) throws Exception;

    @Insert("insert into t_blog(title,context,user_id) values(#{title},#{context},#{userId})")
    void insert(Blog blog) throws Exception;

    @Delete("delete from t_blog where id=#{id}")
    void delete(Integer id) throws Exception;

    @Update("update t_blog set title=#{title},context=#{context},count=#{count} where id=#{id}")
    void update(Blog blog) throws Exception;

}
