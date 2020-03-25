package club.wlqzz.blog.mapper;

import club.wlqzz.blog.pojo.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouceMapper {
    @Insert("insert into t_announcement(title,context,user_id) values(#{title},#{context},#{userId})")
    void insertAnnoucement(Announcement announcement) throws Exception;

    @Delete("delete from t_announcement where id=#{id}")
    void deleteAnnoucement(Integer id) throws Exception;

    @Update("update t_announcement set title=#{title},context=#{context},count=#{count} where id=#{id}")
    void updateAnnoucement(Announcement announcement) throws Exception;

    @Select("select * from t_announcement where user_id=#{userId}")
    List<Announcement> selectAnnoucements(Integer user_id) throws Exception;

    @Select("select * from t_announcement")
    List<Announcement> selectAll() throws Exception;

    @Select("select * from t_announcement where id=#{id}")
    Announcement selectAnnoucement(Integer id) throws Exception;


}
