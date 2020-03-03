package club.wlqzz.blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 权限对应实体类
 */
@Data
public class Permission implements Serializable {
    private Integer id;//主键.
    private String name;//名称.
    private String url;//资源路径.
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Set<Role> roles;
}
