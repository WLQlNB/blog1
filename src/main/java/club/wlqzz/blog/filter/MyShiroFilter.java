package club.wlqzz.blog.filter;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyShiroFilter{

/*
    private static final Logger log = LoggerFactory.getLogger(MyShiroFilter.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        Map<String,Filter> map = new LinkedHashMap<String,Filter>();
        map.put("authc",getFormAuthenticationFilter());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/user/**", "anon");
        //配置在最后面
        filterChainDefinitionMap.put("/**", "authc");
        //登录的URL接口（Shiro可以进行识别）
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //这个map中包含了上面自定义的信息，配置到setFilter中
        shiroFilterFactoryBean.setFilters(map);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;

    }

    *//**开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     *//*
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    FormAuthenticationFilterOverrite getFormAuthenticationFilter(){

        FormAuthenticationFilterOverrite authenticating = new FormAuthenticationFilterOverrite();

        return authenticating;
    }*/
}
