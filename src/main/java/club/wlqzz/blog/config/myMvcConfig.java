package club.wlqzz.blog.config;

import club.wlqzz.blog.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

//@Configuration
public class myMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public WebMvcConfigurationSupport webMvcConfigurationSupport() {
        WebMvcConfigurationSupport support = new WebMvcConfigurationSupport() {
            @Override
            protected void addViewControllers(ViewControllerRegistry registry) {
               /* registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("main");
                registry.addViewController("/web/html.html").setViewName("html");
                registry.addViewController("/web/css.html").setViewName("css");
                registry.addViewController("/web/js.html").setViewName("js");
                registry.addViewController("/web/jquery.html").setViewName("jquery");
                registry.addViewController("/web/ajax.html").setViewName("ajax");*/
            }

            @Override
            protected void addResourceHandlers(ResourceHandlerRegistry registry) {
              registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
              super.addResourceHandlers(registry);
            }
        };
        return support;
    }

    @Bean
    public LocaleResolver localeResolver() {

        return new MyLocaleResolver();
    }
}