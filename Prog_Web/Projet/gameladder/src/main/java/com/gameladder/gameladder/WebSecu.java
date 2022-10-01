package com.gameladder.gameladder;

import javax.inject.Inject;

import com.gameladder.gameladder.User.UserService;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecu extends WebSecurityConfigurerAdapter {

        @Inject UserService userDetailsService;

        /* Fonction que permet de garantir les acces en fonction des roles */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                //.antMatchers("/setup/**", "/public", "/public/**").permitAll()
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
