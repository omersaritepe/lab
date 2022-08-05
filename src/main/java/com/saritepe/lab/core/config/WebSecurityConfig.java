package com.saritepe.lab.core.config;

import com.saritepe.lab.core.model.entity.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String userRawPassword = "user";
        String userEncodedPassword = encoder.encode(userRawPassword);

        String adminRawPassword = "admin";
        String adminEncodedPassword = encoder.encode(adminRawPassword);

        auth.authenticationProvider(daoAuthenticationProvider());
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(userEncodedPassword)
                .roles("USER")
                .and()
                .withUser("admin")
                .password(adminEncodedPassword)
                .roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/labWorkers/delete/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/reports/delete/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
