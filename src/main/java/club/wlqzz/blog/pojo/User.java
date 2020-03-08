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
    private String type;
    private String salt;
    private byte state;
    private List<Blog> blogList;
    private Set<Role> roles;
}
