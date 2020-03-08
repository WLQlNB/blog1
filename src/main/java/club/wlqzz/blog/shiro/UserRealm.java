package club.wlqzz.blog.shiro;

import club.wlqzz.blog.pojo.Permission;
import club.wlqzz.blog.pojo.Role;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.AdminService;
import club.wlqzz.blog.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===执行授权===");
/*
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色与权限字符串集合
            Set<String> rolesCollection = new HashSet<>();
            Set<String> premissionCollection = new HashSet<>();
            // 读取并赋值用户角色与权限
            Set<Role> roles = user.getRoles();
            for(Role role : roles){
                rolesCollection.add(role.getName());
                Set<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions){
                    premissionCollection.add(permission.getUrl());
                }
                info.addStringPermissions(premissionCollection);
            }
            info.addRoles(rolesCollection);
            return info;
        }*/
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = null;
        try {
            user = loginService.getUserByName(token.getUsername());
            if (user == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(String.valueOf(user.getId()));
        return new SimpleAuthenticationInfo(user, user.getPassword(),
                credentialsSalt, getName());
    }
}
