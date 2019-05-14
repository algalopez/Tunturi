package com.ranking.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserService implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        Notice that UserDetailsService needs to implement only one method - loadUserByUsername. This method is used to determine which user is logged in, asuming that every user has a unique username.
         */

        if ("user".equals(username)) {
//            org.springframework.security.core.userdetails.User.UserBuilder =
//            User.UserBuilder users = User.withDefaultPasswordEncoder();
            return new User();
        }
        throw new UsernameNotFoundException("asd");
    }
}
