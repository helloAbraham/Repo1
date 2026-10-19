package com.MyImageUpload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MyImageUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyImageUploadApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/images/upload").allowedOrigins("http://localhost:8082");
//				registry.addMapping("/api/images/contact").allowedOrigins("http://localhost:8082");
//			}
//		};
//	}

}
