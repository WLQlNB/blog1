package club.wlqzz.blog.controller;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.BlogService;
import club.wlqzz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    public static final String SCORE_RANK = "score_rank";

    @PostMapping("/loginCheck")
    public String login(@RequestParam("all") String all,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) throws Exception {
        User user = null;
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String zh = "^\\d{8,}$";
        if (all.matches(em)) {
            user = userService.selectUser(all);
        } else if (all.matches(zh)) {
            user = userService.selectUser(Integer.parseInt(all));
        }else {
            model.addAttribute("error", "error");
            return "login";
        }
        if (userService.checkLogin(user.getId(), user.getEmail(),password)) {
            session.setAttribute("loginUser", user);
            return "main";
        } else {
            model.addAttribute("error", "error");
            return "login";
        }
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }


    @GetMapping(value = {"/", "/main"})
    public String toMain(Model model) throws Exception {
        List<Blog> blogList = blogService.selectAll();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        for (Blog blog: blogList) {
            zSetOperations.add("blogRank",blog.getTitle(),blog.getCount());
        }
        Set countRank= zSetOperations.reverseRange("blogRank",0,2);
     /*   for (Blog blog:
            countRank ) {
            System.out.println("热门排行"+blog);
        }*/
        model.addAttribute("countRank",countRank);
        model.addAttribute("blogList", blogList);
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
