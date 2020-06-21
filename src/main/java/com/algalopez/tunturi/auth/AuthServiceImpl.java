package com.algalopez.tunturi.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Slf4j
public class AuthServiceImpl implements UserDetailsService {

    private final AuthDao authDao;

    public AuthServiceImpl(@Autowired AuthDao authDao) {
        this.authDao = authDao;
    }

    /**
     * Determine which user is logged in, assuming that every user has a unique username.
     *
     * @param username unique username
     * @return user principal
     * @throws UsernameNotFoundException if user does not exist
     */
    @Override
    public Auth loadUserByUsername(String username) {

        Auth auth;
        try {
            auth = (Auth) authDao.findUserByUsername(username);
        } catch (EmptyResultDataAccessException e) {
            log.debug("User {} does not exist", username);
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return auth;
    }
}
