package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.BlogMapper;
import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.service.BlogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class BlogServiceImplTest {

    @Autowired
    private BlogService blogService;

    @Test
    public void selectBlogLatest() throws Exception {
        List<Blog> blogList=blogService.selectAll();
        for (Blog blog :
                blogList) {
            System.out.println(blog);
        }
    }
}