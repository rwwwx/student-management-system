package com.example.annotationtest.config;

import com.example.annotationtest.entity.UserRole;
import com.example.annotationtest.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsServiceImpl myUserDetailsService;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl myUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(bCryptPasswordEncoder.encode("1"))
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .roles(UserRole.ADMIN.name())
                .and()
                .withUser("user")
                .password(bCryptPasswordEncoder.encode("1"))
                .roles(UserRole.USER.name())
                .authorities(UserRole.USER.getGrantedAuthorities());
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/signIn").permitAll()
                .antMatchers("/*").hasAnyAuthority("student:read", "student:write")
                .and()
                .httpBasic();
        http
                .logout().logoutUrl("/logout");
    }

}
