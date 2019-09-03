package club.wlqzz.blog.controller;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/user")
    public String toUserPage(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") != null) {
            User user = (User) session.getAttribute("loginUser");
            model.addAttribute("user", user);
            return "user/myInfor";
        }
        return "/login";
    }

    @GetMapping("/article")
    public String lookMyArticle(HttpSession session, Model model) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        List<Blog> aList = blogService.selectAllUser(user.getId());
        model.addAttribute("aList", aList);
        return "user/article";
    }

    @GetMapping("/editArticle/{id}")
    public String editArticle(@PathVariable("id") Integer id, Model model) throws Exception {
        Blog blog = blogService.selectBlog(id);
        model.addAttribute("blog", blog);
        return "user/editArticle";
    }

    @PostMapping("/updateArticle")
    public String updateArticle(Blog blog) throws Exception {
        blogService.updateBlog(blog);
        return "redirect:/article";
    }

    @GetMapping("/deleteArticle")
    public String deleteArticle(Blog blog) throws Exception {
        blogService.deleteBlog(blog.getId());
        return "redirect:/article";
    }

    @GetMapping("/writeBlog")
    public String towriteBlog(HttpSession session){
        if(session.getAttribute("loginUser")!=null)
            return "user/writeBlog";
        return "login";
    }
}
