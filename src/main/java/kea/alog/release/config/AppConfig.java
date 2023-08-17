package kea.alog.release.config;

import kea.alog.release.feign.NotiFeign;
import kea.alog.release.web.DTO.NotiDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public String myStringBean() {
        return new String();
    }

//    @Bean
//    public NotiFeign notiFeign(){
//        return new NotiFeign() {
//            @Override
//            public String create(NotiDto.MessageDto message) {
//                return null;
//            }
//        };
//    }
}
