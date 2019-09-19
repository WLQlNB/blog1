package club.wlqzz.blog.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class User {
    @Getter@Setter private Integer id;
    @Getter@Setter private String name;
    @Getter@Setter private char sex;
    @Getter@Setter private int age;
    @Getter@Setter private String password;
    @Getter@Setter private List<Blog>blogList;
    @Getter@Setter private String email;

}
