package club.wlqzz.blog.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private Integer id;
    private String title;
    private String context;
    private Date date;
    private int count;
    private Integer userId;
    private User user;

}
