package club.wlqzz.blog.mapper;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {

    @Select("select count(id) from t_user where type=#{type}")
    int selectUserCount(String type);

    @Select("select count(id) from t_blog")
    int selectBlogCount();

    @Select("select * from t_blog")
    List<Blog> selectAllBlog();

    @Select("")
    List<Blog> selectBlogPages();

}
