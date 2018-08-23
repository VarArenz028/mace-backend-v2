package com.basicauth.service;

import com.basicauth.data.User;
import com.basicauth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Giancarlo Angulo.
 */
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername",username);
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                System.out.println("user not found with the provided username");
                logger.info("user not found with the provided username",username);
                return null;
            }
            System.out.println(" user from username " + user.toString());
            logger.info("user from username",user);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        } catch (Exception e) {
            logger.info("loadUserByUsername",e);
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        logger.info("getAuthorities",user);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        logger.info("getAuthorities",authorities);
        System.out.println("user authorities are " + authorities.toString());
        return authorities;
    }


}
