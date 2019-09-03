package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.BlogMapper;
import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void addBlog(Blog blog) throws Exception {
        blogMapper.insert(blog);
    }

    @Override
    public void deleteBlog(Integer id) throws Exception {
        blogMapper.delete(id);
    }

    @Override
    public void updateBlog(Blog blog) throws Exception {
        blogMapper.update(blog);
    }

    @Override
    public Blog selectBlog(Integer id) throws Exception {
        return blogMapper.selectById(id);
    }

    @Override
    public List<Blog> selectAll() throws Exception {
        return blogMapper.selectAll();
    }

    @Override
    public List<Blog> selectAllUser(Integer userId) throws Exception {
        return blogMapper.selectAllUser(userId);
    }
}
