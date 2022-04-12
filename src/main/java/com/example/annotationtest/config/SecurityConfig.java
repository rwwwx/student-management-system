package com.example.annotationtest.config;

import com.example.annotationtest.entity.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hibernate.criterion.Restrictions.and;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                .roles(UserRole.USER_ROLE.name())
                .and()
                .withUser("user")
                .password(bCryptPasswordEncoder.encode("user"))
                .roles(UserRole.USER_ROLE.name(), UserRole.USER_ROLE.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/signIn").permitAll()
                .antMatchers("/student/*", "/subject/*").hasAnyRole(UserRole.USER_ROLE.name(), UserRole.ADMIN_ROLE.name())
                .antMatchers("/admin/getAllStudents").hasRole(UserRole.ADMIN_ROLE.name())
                .and().formLogin();
        http
                .logout().logoutUrl("/logout");
    }

}
