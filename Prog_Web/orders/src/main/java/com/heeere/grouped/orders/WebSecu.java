package com.heeere.grouped.orders;

import javax.inject.Inject;

import com.heeere.grouped.orders.model.user.UserService;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecu extends WebSecurityConfigurerAdapter {

        @Inject
        UserService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                //.antMatchers("/SETUP/**", "/public", "/public/**").permitAll()
                .antMatchers("/", "/public/**", "/css/**").permitAll()
                .antMatchers("/ordering/**").authenticated()
                .antMatchers("/manage/**", "/admin/**").hasRole("ADMIN");
                http.formLogin().and().logout()
                ;
                // allow access to the h2-console, despite spring security being active, ref https://stackoverflow.com/a/40168549/2297277 for the frameOptions
                http.authorizeRequests().antMatchers("/h2c/**").hasRole("ADMIN");
                http.csrf().ignoringAntMatchers("/h2c/**");
                http.headers().frameOptions().sameOrigin();

                http.authorizeRequests().anyRequest().denyAll();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(userDetailsService.encoder);
        }
}