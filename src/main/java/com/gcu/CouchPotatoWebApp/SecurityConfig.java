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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserBusinessService service;

    @Autowired
    private HttpSecurity http;

    /**
     * Configures the security filter chain.
     *
     * @return the built security filter chain.
     * @throws Exception if an error occurs.
     */
    @Bean
    public SecurityFilterChain securityFilterChain() throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/service/**").authenticated()
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

    /**
     * Bean for password encoding using BCrypt.
     *
     * @return a BCrypt password encoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the authentication manager builder with user details service and password encoder.
     *
     * @param auth the authentication manager builder.
     * @throws Exception if an error occurs.
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder);
    }

    /**
     * Customizes web security to ignore certain static resources.
     *
     * @return a web security customizer.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**");
    }
}
