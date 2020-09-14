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
        registry.addMapping("/**");
    }
}
