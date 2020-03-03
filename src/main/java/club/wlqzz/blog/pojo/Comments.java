package club.wlqzz.blog.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comments implements Serializable {
    private int id;
    private String context;
    private int userId;
    private int blogId;
}
