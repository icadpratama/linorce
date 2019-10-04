package com.lawencon.linov.outsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan( basePackageClasses = {
        OutsourceApplication.class,
        Jsr310JpaConverters.class
})
@EnableSwagger2
public class OutsourceApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
    }

    public static void main(String[] args) {
        SpringApplication.run(OutsourceApplication.class, args);
    }

    @Bean
    public Docket approval() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lawencon.linov.outsource.controller.approval"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Approval")
                .apiInfo(apiInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }

    @Bean
    public Docket attendance() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lawencon.linov.outsource.controller.attendance"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Attendance")
                .apiInfo(apiInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }

    @Bean
    public Docket claim() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lawencon.linov.outsource.controller.claim"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Claim")
                .apiInfo(apiInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }

    @Bean
    public Docket leave() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lawencon.linov.outsource.controller.leave"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Leave")
                .apiInfo(apiInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }

    @Bean
    public Docket authentication() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lawencon.linov.outsource.controller.authentication"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Authentication")
                .apiInfo(apiInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Linorce",
                "Outsource Management System.",
                "0.1.0",
                "Terms of Service",
                new Contact("Irsyad Jamal Pratama Putra", "www.lawencon.com", "irsyadjpratamap@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
