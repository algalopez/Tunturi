package com.algalopez.tunturi.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthDao {

    UserDetails findUserByUsername(String username);

}
