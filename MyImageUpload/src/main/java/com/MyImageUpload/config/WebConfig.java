package com.MyImageUpload.config;
/**
 Instead of putting @CrossOrigin on every controller, we can configure it globally:
 Also NOTE: that expecting subdomain for these site.
 "https://*.abrahamparker.com", for example, api.abrahamparekr.com
 +*/


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "https://m2musa.tv",
                        "https://*.m2musa.tv",

                        "https://abrahamparker.com",
                        "https://*.abrahamparker.com",

                        "https://probasalapon.com",
                        "https://*.probasalapon.com",
                        "https://jfmedia.info",
                        "https://*.jfmedia.info"
                )
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}


