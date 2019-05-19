package com.algalopez.javaboilerplate.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao {

    UserDetails findByUsername(String username);
}
