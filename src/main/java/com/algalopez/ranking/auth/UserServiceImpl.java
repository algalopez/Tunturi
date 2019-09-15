package com.algalopez.ranking.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    private UserAuthDao userAuthDao;

    public UserServiceImpl(@Autowired UserAuthDao userAuthDao) {
        this.userAuthDao = userAuthDao;
    }

    /**
     * Determine which user is logged in, asuming that every user has a unique username.
     * @param username unique username
     * @return user principal
     * @throws UsernameNotFoundException if user does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        UserAuth userAuth;
        try {
            userAuth = (UserAuth) userAuthDao.findByUsername(username);
        } catch (EmptyResultDataAccessException e) {
            log.debug("User {} does not exist", username);
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return userAuth;
    }
}
