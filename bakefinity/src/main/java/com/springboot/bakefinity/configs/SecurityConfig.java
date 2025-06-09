package com.springboot.bakefinity.configs;


import com.springboot.bakefinity.security.filter.JwtSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtSecurityFilter jwtSecurityFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtSecurityFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/*").permitAll()
                        .requestMatchers("/profile/**").permitAll()
                        .requestMatchers("/swagger-ui/*").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/v3/**").permitAll()
                        .requestMatchers("/products/*").permitAll()
                        .requestMatchers("/categories/*").permitAll()
                        .requestMatchers("/admin/**").permitAll()
                        .anyRequest().hasAnyAuthority("ROLE_USER","ROLE_ADMIN","ROLE_MANAGER")
                )
                .httpBasic(httpBasic -> httpBasic.disable());  // <--- Disable Basic Auth explicitly

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        // If you're calling from localhost:4200, prefer this
//        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
//
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true); // only if you send cookies or Authorization
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }



}
