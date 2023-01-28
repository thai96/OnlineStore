//package com.example.mynote.utils;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import java.util.Collections;
//
//@Configuration
//public class SwaggerConfig {
//    public ApiInfo apiInfo(){
//        return new ApiInfo("App Rest Apis",
//                "Apis for app",
//                "1.0",
//                "term of service",
//                new Contact("test", "www.org.com","test@gmail.com"),
//                "Licence of Apis",
//                "API license URL",
//                Collections.emptyList());
//    }
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//}
