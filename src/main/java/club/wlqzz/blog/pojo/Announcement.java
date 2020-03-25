package club.wlqzz.blog.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Announcement implements Serializable {
    private Integer id;
    private String title;
    private String context;
    private Date date;
    private int count;
    private Integer userId;
}
