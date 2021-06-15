package com.which.forensics.config;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() throws IOException {
        MavenXpp3Reader reader = new MavenXpp3Reader();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                .title("Forensics API Documention")
                .description("APIs to find the missing person")
                .contact(new Contact("Which Team", null, "info@which.com"))
                .build());
    }
}
