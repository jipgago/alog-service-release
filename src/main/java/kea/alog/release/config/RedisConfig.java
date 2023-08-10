package kea.alog.release.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import org.springframework.beans.factory.annotation.Value;

@EnableRedisRepositories
@Configuration
public class RedisConfig {
    
    @Value("${spring.redis.lettuce.port}")
    private int port;
    
    @Value("${spring.redis.lettuce.host}")
    private String host;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(host, port);
    }
}
