package club.wlqzz.blog.mapper;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Test

    public void selectAll() throws Exception {
       List<User> list= userMapper.selectAll();
        for (User user:
             list) {
            System.out.println(user);
        }
    }


    @Test
    public void setUser() throws Exception {
      User user= userMapper.selectUser(1,"2065795207@qq.com","e10adc3949ba59abbe56e057f20f883e");
        System.out.println(user);
    }
}