package com.smplatform.member_service.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("member-서비스 API 문서")
                .version("v0.1.0")
                .description("member-서비스 API 문서");
        return new OpenAPI().info(info);
    }
}
