package com.algalopez.ranking.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthDao {

    UserDetails findByUsername(String username);

    Long createUserAuth(String username, String password, UserAuthRole role);
}
