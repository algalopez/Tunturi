package com.algalopez.ranking.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthDao {

    UserDetails findUserByUsername(String username);

}
