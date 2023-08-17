package kea.alog.release.config;

import feign.RequestInterceptor;
import kea.alog.release.config.Interceptor.JwtRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtRequestFeignConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new JwtRequestInterceptor();
    }
}
