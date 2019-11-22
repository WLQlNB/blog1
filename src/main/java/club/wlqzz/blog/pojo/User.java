package club.wlqzz.blog.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String name;
    private char sex;
    private int age;
    private String password;
    private List<Blog> blogList;
    private String email;

}
