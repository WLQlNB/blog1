package club.wlqzz.blog.controller.admin;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.AdminService;
import club.wlqzz.blog.service.BlogService;
import club.wlqzz.blog.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AdminService adminService;

    @GetMapping("/userInfo")
    public String userManage(Integer id, Model model) throws Exception {
        Blog blog = blogService.selectBlog(id);
        User user = userService.selectUser(blog.getUserId());
        model.addAttribute("user", user);
        return "admin/userInfo";
    }

    @GetMapping("/deleteBlog")
    public String deleteBlog(Integer id) throws Exception {
        Blog blog = blogService.selectBlog(id);
        redisTemplate.opsForZSet().remove("blogRank", blog.getTitle());
        blogService.deleteBlog(id);
        return "forward:/admin/blogManage";
    }

    @GetMapping("/admin/blogManage")
    public String blogManage(Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) throws Exception {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogList = adminService.selectAllBlog();
        Integer blogSum = adminService.selectBlogCount();
        model.addAttribute("blogList", blogList);
        model.addAttribute("blogSum", blogSum);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogManage";
    }

    @GetMapping("/admin/lookBlog")
    public String lookBlog(Integer id,Model model) throws Exception {
        Blog blog=blogService.selectBlog(id);
        model.addAttribute("blog",blog);
        return "admin/lookArticle";
    }

}
