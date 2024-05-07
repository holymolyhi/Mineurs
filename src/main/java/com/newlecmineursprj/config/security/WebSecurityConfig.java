package com.newlecmineursprj.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers("/admin/**","/notices/reg").hasRole("ADMIN")
                        .requestMatchers("/myshop/**").hasRole("MEMBER")
                        .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/signin")
                        .failureHandler(new LoginAuthenticFailure())
                        .permitAll())
                .logout((logout) -> logout
                        .invalidateHttpSession(true)
                        .logoutUrl("/signout")
                        .logoutSuccessUrl("/")
                        .permitAll());
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setHideUserNotFoundExceptions(false);  // This is important
        return authenticationProvider;
    }
}
