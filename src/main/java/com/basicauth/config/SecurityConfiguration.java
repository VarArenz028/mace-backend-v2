package com.basicauth.config;

/**
 * Created by Giancarlo Angulo.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("configure");

        http.csrf().disable().httpBasic().and()
                .authorizeRequests()
                .antMatchers("/register/").permitAll()
                .antMatchers("/mace/**").permitAll()
                .antMatchers("/membership/**").permitAll()
                .antMatchers("/moss/**").permitAll()
                .antMatchers("/viewaccountinfo/**").permitAll()
                .antMatchers("/loginuser/**").permitAll()
                .antMatchers("/getmemberutil/**").hasAuthority("ROLE_USER")
                .antMatchers("/upload/**").permitAll()
//                .antMatchers("/verifyAccount/**").hasAuthority("ROLE_USER")
//                .antMatchers("/skipVerification/**").hasAuthority("ROLE_USER")
                .antMatchers("/verifyAccount/**").permitAll()
                .antMatchers("/skipVerification/**").permitAll()
                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/deletepicture/**").hasAuthority("ROLE_USER")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
