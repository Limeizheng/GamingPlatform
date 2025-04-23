package com.cloud.shopping.upload.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.Add CORS configuration information
        CorsConfiguration config = new CorsConfiguration();
        //1) For the allowed domains, do not write *, otherwise the cookie will not be usable
        config.addAllowedOrigin("http://manage.cloudshopping.com");
        config.addAllowedOrigin("http://www.cloudshopping.com");
        config.addAllowedOrigin("http://api.cloudshopping.com");
        config.addAllowedOrigin("*");
        //2) Whether to send Cookie information
        config.setAllowCredentials(false);
        //3) Allowed request methods
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedHeader("*");

        //2.Add the mapping path and we intercept all requests
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.Return the new CorsFilter.
        return new CorsFilter(configSource);
    }
}
