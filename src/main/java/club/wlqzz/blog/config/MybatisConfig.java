package club.wlqzz.blog.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);//设置驼峰命名规则
            }
        };
    }


}
