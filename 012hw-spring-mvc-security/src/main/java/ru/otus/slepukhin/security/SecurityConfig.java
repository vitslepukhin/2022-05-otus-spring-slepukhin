package ru.otus.slepukhin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin((form) -> form.permitAll()).logout((logout) -> logout.permitAll())
                .authorizeHttpRequests((requests) -> requests.antMatchers("/").permitAll())
                .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());

        return http.build();
    }
}