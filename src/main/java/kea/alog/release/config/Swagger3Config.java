package kea.alog.release.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import java.util.Arrays;

@Configuration
public class Swagger3Config {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Release API")
                .version("1.0.0")
                .description("API Release domain");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer", securityScheme))
                .security(Arrays.asList(securityRequirement))
                .info(info);
    }
}
