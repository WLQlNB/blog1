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
    public void selectBlogById() throws Exception {
        Blog blog=blogMapper.selectById(1);
        System.out.println("我的博客");
        System.out.println(blog);
        System.out.println(blog.getUserId());
    }

    @Test
    public void deleteBlog() throws Exception {
        blogMapper.delete(10);
    }
}