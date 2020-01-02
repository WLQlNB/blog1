package club.wlqzz.blog.shiro;

import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class MyShiroRealmService extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealmService.class);


    @Autowired
    private UserService userService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // TODO 自动生成的方法存根
        String username = (String) token.getPrincipal();
        log.info("token带来的数据：  " + username);

        User user = null;
        try {
            String passwordDataSource = userService.selectUser(username).getPassword();
            log.info("从数据库中查询到的数据密码：{}", passwordDataSource);
            user = userService.selectUser(username);
            log.info("user：{}", user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user, //用户对象--数据库
                user.getPassword(), //密码--数据库
                ByteSource.Util.bytes(user.getSalt()),
                getName()  //realm name
        );
        return simpleAuthenticationInfo;
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO 自动生成的方法存根
        //改掉null
        //查询数据库获取角色和权限信息
        //SimpleAuthorizationInfo a = new SimpleAuthorizationInfo();
//		a.setRoles(roles);
        return null;

    }
}

