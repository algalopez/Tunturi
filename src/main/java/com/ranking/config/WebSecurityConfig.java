package com.ranking.config;

import com.ranking.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.httpBasic().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/public").permitAll()
//                .antMatchers("/api/admin").access("hasRole('ADMIN')")
//                .anyRequest().authenticated();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(ENCODER_STRENGTH);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER", "ADMIN");
//    }

}
