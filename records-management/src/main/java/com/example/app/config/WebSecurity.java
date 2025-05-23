package com.example.app.config;

import com.example.app.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurity(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/welcome","/webjars/**", "/css/**", "/js/**", "/img/**","/error").permitAll()
                        .requestMatchers("/new-user", "/register").permitAll()
                        .requestMatchers("/dashboard/**").authenticated()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Default logout URL
                        .logoutSuccessUrl("/login?logout")  // Redirect here after logout
                        .invalidateHttpSession(true)  // Invalidate session
                        .deleteCookies("JSESSIONID")  // Optional: delete cookies
                )
                .userDetailsService(customUserDetailsService) // Use custom UserDetailsService
                .build();
    }

    @Bean
    @Lazy
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for hashing passwords
    }
}
