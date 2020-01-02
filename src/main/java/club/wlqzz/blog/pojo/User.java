package club.wlqzz.blog.pojo;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class User {
    private Integer id;
    private String name;
    private char sex;
    private int age;
    private String password;
    private List<Blog> blogList;
    private String email;
    private Set<Role> roles;
    private String salt;
}
