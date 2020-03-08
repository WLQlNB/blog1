package club.wlqzz.blog.mapper;


import club.wlqzz.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from t_user")
    List<User> selectAll() throws Exception;

    @Select("select * from t_user where id=#{id}")
    User selectById(Integer id) throws Exception;

    @Select("select * from t_user where email=#{email}")
    User selectByEmail(String email) throws Exception;

    @Select("select * from t_user where (email=#{email} and password=#{password})" +
            "or(id=#{id} and password=#{password})")
    User selectUser(Integer id, String email, String password) throws Exception;

    @Insert("insert into t_user(name,age,sex,password,email,type) values(#{name},#{age},#{sex},#{password},#{email},#{type})")
    void insert(User user) throws Exception;

    void delete(User user) throws Exception;

    @Update("update t_user set name=#{name},age=#{age},sex=#{sex},email=#{email},password=#{password} where id=#{id}")
    void update(User user) throws Exception;

}
