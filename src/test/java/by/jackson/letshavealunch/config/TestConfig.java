package by.jackson.letshavealunch.config;

import by.jackson.letshavealunch.util.JpaUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public JpaUtil jpaUtil() {
        return new JpaUtil();
    }
}
