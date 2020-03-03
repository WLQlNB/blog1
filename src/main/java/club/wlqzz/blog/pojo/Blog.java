package club.wlqzz.blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Blog implements Serializable {
    private Integer id;
    private String title;
    private String context;
    private Date date;
    private int count;
    private Integer userId;
    private User user;
    private List<Comments> blogComments;
}
