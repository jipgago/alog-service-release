package kea.alog.release.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
        .title("Alog Release API")
        .description("Alog Release API")
        .version("1.0.0");

        return new OpenAPI()
        .components(new Components())
        .info(info);
    }

    @Bean
    public GroupedOpenApi releaseGroup(){
        return GroupedOpenApi.builder()
        .group("post")
        .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Release API").version("1.0.0").description("Release API")))
        .build();
    }
}
