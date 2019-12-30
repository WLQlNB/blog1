package club.wlqzz.blog.mapper;

import club.wlqzz.blog.pojo.Diary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryMapper {
    @Select("select * from t_diary")
    List<Diary> selectAll() throws Exception;

    @Select("select * from t_diary where user_id=#{userId}")
    List<Diary> selectAllDiary(Integer user_id) throws Exception;

    @Select("select * from t_diary where id=#{id}")
    Diary selectById(Integer id) throws Exception;

    @Insert("insert into t_diary(title,context,user_id) values(#{title},#{context},#{userId})")
    void insert(Diary diary) throws Exception;

    @Delete("delete from t_diary where id=#{id}")
    void delete(Integer id) throws Exception;

    @Update("update t_diary set title=#{title},context=#{context} where id=#{id}")
    void update(Diary diary) throws Exception;

}
