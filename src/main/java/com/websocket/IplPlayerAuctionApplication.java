package com.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IplPlayerAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(IplPlayerAuctionApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
                .allowedOrigins("*") // Update with your allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Update with your allowed HTTP methods
                .allowedHeaders("*"); // Update with your allowed headers
			}
		};
	}

}
