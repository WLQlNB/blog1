package club.wlqzz.blog.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
public class Blog {
    @Getter@Setter private Integer id;
    @Getter@Setter private String title;
    @Getter@Setter private String context;
    @Getter@Setter private Date date;
    @Getter@Setter private int count;
    @Getter@Setter private Integer userId;
    @Getter@Setter private User user;

}
