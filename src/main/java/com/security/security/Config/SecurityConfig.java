package com.security.security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define user details in-memory
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("lalith")
                        .password(passwordEncoder.encode("password")) // ENCODED password
                        .roles("USER")
                        .build()
        );
    }



    // Define security filter chain (latest style)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // modern way to disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public").permitAll()   // open endpoint
                        .requestMatchers("/private").authenticated()             // everything else requires login
                )
                .formLogin(Customizer.withDefaults())
        ; // enable HTTP Basic Auth

        return http.build();
    }
}
