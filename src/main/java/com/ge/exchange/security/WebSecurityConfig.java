package com.ge.exchange.security;

import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/request/get/{id}", "/request/getAll").hasRole("ADMIN")
        .antMatchers( "/post/get/{id}", "/post/getAll").hasRole("USER")
        .antMatchers("/requestddd/*", "/postdddd/*", "/chat", "/chat1/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/register/**", "/login").permitAll()
        .antMatchers("/hello").hasRole("USER")
        .antMatchers("/").permitAll()
        .anyRequest()
        .authenticated()
        .and()
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")

                                .successHandler((request, response, authentication) -> {
                                    log.info("User '{}' has been authenticated successfully", authentication.getName());
                                })
                                .defaultSuccessUrl("/home",true)
                                .permitAll()
                )

                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
    }
}