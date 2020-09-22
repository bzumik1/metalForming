package com.siemens.metal_forming;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
//@EnableWebMvc //should not be activated, disables auto-configuration
public class ApiConfiguration implements WebMvcConfigurer {
    @Override //api is not case sensitive
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    @Override //allows CORS from all origins
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //specifies allowed origins (ALL BY DEFAULT!)
                .allowedMethods("POST","GET","OPTIONS","DELETE","PUT") //specifies methods which can be used for CORS
                //.allowedHeaders("content-type","authorization","Content-IntType","Accept","X-Requested-With","remember-me","x-xsrf-token","XDataConnectorTraceskip")
                //.allowCredentials(true)
                .maxAge(3600); //specifies for how long preflight of CORS can be cashed in seconds
    }
}
