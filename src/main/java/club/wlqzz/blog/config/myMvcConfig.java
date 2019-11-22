package club.wlqzz.blog.config;

import club.wlqzz.blog.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class myMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> includePathLists = new ArrayList<>();
        includePathLists.add("/user");
        includePathLists.add("/user/**");
        includePathLists.add("/writeBlog");
        includePathLists.add("/diary");
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns(includePathLists);
    }

}