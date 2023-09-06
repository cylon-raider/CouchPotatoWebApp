package com.gcu.CouchPotatoWebApp;

import com.gcu.CouchPotatoWebApp.business.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserBusinessService service;

    @Autowired
    private HttpSecurity http;

    @Bean
    public SecurityFilterChain securityFilterChain() throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/service/**").authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/registration/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/index", true)
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**");
    }
}
