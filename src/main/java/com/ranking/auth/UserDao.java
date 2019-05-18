package com.ranking.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao {

    UserDetails findByUsername(String username);
}
