package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.AdminMapper;
import club.wlqzz.blog.pojo.Announcement;
import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int selectUserCount(String type) throws Exception {
        return adminMapper.selectUserCount(type);
    }

    @Override
    public int selectBlogCount() throws Exception {
        return adminMapper.selectBlogCount();
    }

    @Override
    public List<Blog> selectAllBlog() {
        return adminMapper.selectAllBlog();
    }

    @Override
    public User selectUser(Integer id) throws Exception {
        return adminMapper.selectUser(id);
    }

    @Override
    public void deleteUser(Integer id) throws Exception {
        adminMapper.deleteUser(id);
    }

}
