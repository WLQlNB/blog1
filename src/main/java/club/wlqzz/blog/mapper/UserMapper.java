package club.wlqzz.blog.mapper;


import club.wlqzz.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    List<User> selectAll() throws Exception;

    @Select("select * from t_user where id=#{id}")
    User selectById(Integer id) throws Exception;

    @Select("select * from t_user where email=#{email}")
    User selectByEmail(String email) throws Exception;


    @Insert("insert into t_user(name,age,sex,password,email) values(#{name},#{age},#{sex},#{password},#{email})")
    void insert(User user) throws Exception;

    void delete(User user) throws Exception;

    void update(User user) throws Exception;

}
