package kea.alog.release.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public String myStringBean() {
        return new String();
    }
}
