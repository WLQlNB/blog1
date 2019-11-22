package club.wlqzz.blog.config;


import club.wlqzz.blog.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

//@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean registFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(this.LoginFilter());
//        registration.addUrlPatterns("/user/*");
//        registration.setName("LoginFilter");
//        registration.setOrder(1);
//        return registration;
//    }
//
//    @Bean
//    public Filter LoginFilter() {
//        return new LoginFilter();
//    }

}
