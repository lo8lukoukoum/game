package com.example.gameshare.config;

import com.example.gameshare.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Autowired
    public WebConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**") // Apply to all paths
                .excludePathPatterns( // List of paths to exclude from interception (publicly accessible)
                        "/login",
                        "/register",
                        "/logout", // Logout should be accessible to trigger logout
                        "/",
                        "/games",
                        "/games/search",
                        "/games/category/**",
                        "/games/{id}", // Viewing game details is public
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/favicon.ico",
                        "/error", // Spring Boot error handling page
                        "/access-denied"
                );
        // Note: /games/{id}/comments and /games/{id}/ratings are not excluded here.
        // The interceptor's preHandle logic will allow GET requests for these
        // (for viewing comments/ratings) but will protect POST requests (for creating).
    }
}
