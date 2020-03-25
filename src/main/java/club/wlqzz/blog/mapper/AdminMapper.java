package club.wlqzz.blog.mapper;

import club.wlqzz.blog.pojo.Announcement;
import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

   @Select("select * from t_user where id=#{id}")
    User selectUser(Integer id);

   @Delete("delete u,b,c,r,d from t_user  u" +
           " left join t_blog b on u.id=b.user_id" +
           " left join t_comments c on u.id=c.user_id" +
           " left join t_reply r on u.id=r.user_id" +
           " left join t_diary d on u.id=d.user_id" +
           " where u.id=#{id}")
    void deleteUser(Integer id);


}
