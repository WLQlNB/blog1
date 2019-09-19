package club.wlqzz.blog.controller;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.BlogService;
import club.wlqzz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @PostMapping("/loginCheck")
    public String login(@RequestParam("id") Integer id,
                        @RequestParam("password") String password,String email,
                        Map<String, Object> map, HttpSession session) throws Exception {

        User user = userService.selectUser(id);
        if (userService.checkLogin(id,email, password)) {
            session.setAttribute("loginUser", user);
            return "main";
        } else {
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }


    @GetMapping(value = {"/", "/main"})
    public String toMain(Model model) throws Exception {
        List<Blog> blogList=blogService.selectAll();
        model.addAttribute("blogList",blogList);
        return "main";
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            session.invalidate();
        }
        return "redirect:/main";
    }

}
