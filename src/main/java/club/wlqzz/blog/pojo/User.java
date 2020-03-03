package club.wlqzz.blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private char sex;
    private int age;
    private String password;
    private String email;
    private String salt;//加密密码的盐
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private List<Blog> blogList;
    private Set<Role> roles;
}
