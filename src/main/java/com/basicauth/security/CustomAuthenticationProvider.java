package com.basicauth.security;

import com.basicauth.data.AppUser;
import com.basicauth.data.User;
import com.basicauth.repository.AppUserRepository;
import com.basicauth.repository.UserRepository;
import com.basicauth.service.MaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlo Angulo.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MaceService maceService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authenticate");
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUsername(name);
        AppUser appUser = maceService.findbyUsername(name);
        System.out.println(appUser);
        System.out.println(user);
        if(appUser != null){

            System.out.println(password);
            System.out.println(appUser.getPassword());
            System.out.println(passwordEncoder().matches(password,appUser.getPassword()));
            if (!passwordEncoder().matches(password,appUser.getPassword())) {
                if(!password.equalsIgnoreCase(appUser.getPassword())){
                    throw new BadCredentialsException("Wrong password.");
                }
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new UsernamePasswordAuthenticationToken(appUser, password, grantedAuthorities );
        } else  {

            if (user == null || !user.getUsername().equalsIgnoreCase(name)) {
                throw new BadCredentialsException("Username not found.");
            }

            System.out.println(password);
            System.out.println(user.getPassword());
            System.out.println(passwordEncoder().matches(password,user.getPassword()));
            if (!passwordEncoder().matches(password,user.getPassword())) {
                throw new BadCredentialsException("Wrong password.");
            }

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities );
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

