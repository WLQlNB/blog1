package club.wlqzz.blog.controller.user;

import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.MailService;
import club.wlqzz.blog.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
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

    @PostMapping("/doReg")
    public String doReg(String email, String verificationCode, String pwd,Model model) throws Exception {
        User user = new User();
        user.setEmail(email);
        user.setType("user");
        user.setPassword(pwd);
        String ans = stringRedisTemplate.opsForValue().get("verificationCode");
        if(email==null||email==""||email=="null"){
            model.addAttribute("error","请输入邮箱号！");
            return "reg";
        }
        if (userService.selectUser(email)!= null) {
            model.addAttribute("error", "该邮箱号已经存在");
            return "reg";
        }
        if(user.getPassword()==null||user.getPassword()==""||user.getPassword()=="null"){
            model.addAttribute("error","请输入密码！");
            return "reg";
        }
        if (verificationCode!=null&&ans!=null&&ans.equals(verificationCode)) {
            userService.addUser(user);
            User user1=userService.selectUser(email);
            ByteSource credentialsSalt = ByteSource.Util.bytes(String.valueOf(user1.getId()));
            String md5Password = new Md5Hash(pwd, credentialsSalt,1024).toString();
            user1.setPassword(md5Password);
            userService.updateUser(user1);
            return "login";
        }else{
            model.addAttribute("error","验证码错误");
            return "reg";
        }

    }

    @GetMapping("/verification")
    public String Verification(@RequestParam(value = "email") String email, Model model) throws Exception {
      /* User user=userService.selectUser(email);
        if (user!= null) {
            model.addAttribute("error", "该邮箱号已经存在");
            System.out.println("该邮箱号已被注册......");
            return "reg";
        }else {*/
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            stringRedisTemplate.opsForValue().set("verificationCode", checkCode, 300, TimeUnit.SECONDS);
            String message = "您的注册验证码为：" + checkCode + "有效时间5分钟";
            mailService.sendMail(email, "注册验证码", message);
            return "reg";
//        }

    }
}
