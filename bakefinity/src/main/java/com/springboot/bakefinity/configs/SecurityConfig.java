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

        http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtSecurityFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/*").permitAll()
                        .requestMatchers("/admin/*").hasRole("ADMIN")
                        .anyRequest().hasAnyAuthority("ROLE_USER","ROLE_ADMIN","ROLE_MANAGER")
                )
                .httpBasic(httpBasic -> httpBasic.disable());  // <--- Disable Basic Auth explicitly

        return http.build();
    }
}
