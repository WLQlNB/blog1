package club.wlqzz.blog.controller;

import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.MailService;
import club.wlqzz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class RegController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/reg")
    public String toReg() {
        return "reg";
    }

    @PostMapping("/doReg")
    public String doReg(String email, String verificationCode, String pwd, Model model) throws Exception {
        if(userService.selectUser(email)!=null){
            model.addAttribute("error","error");
            return "reg";
        }else {
            User user=new User();
            user.setEmail(email);
            user.setPassword(pwd);
            user.setEmail(email);
            String ans =stringRedisTemplate.opsForValue().get("verificationCode");
            if(ans.equals(verificationCode)){
                userService.addUser(user);
            }
            return "login";
        }
    }

    @GetMapping("/verification")
    public String Verification(@RequestParam(value = "email") String email) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        stringRedisTemplate.opsForValue().set("verificationCode", checkCode,300, TimeUnit.SECONDS);
        String message = "您的注册验证码为："+checkCode+"有效时间5分钟";
        mailService.sendMail(email, "注册验证码", message);
        return "forward:/reg";
    }
}
