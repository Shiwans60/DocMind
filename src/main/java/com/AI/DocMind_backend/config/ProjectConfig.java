package com.AI.DocMind_backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info().title("AI Doc Mind Intelligence and RAG Backend").description("AI Doc Mind Intelligence and RAG Backend").version("1.0").contact(new Contact()
                                .name("DocMind Project")
                                .email("meprm525@gmail.com")
                                .url("https://github.com/Shiwans60")
                        )
                );
    }
}
