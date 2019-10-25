package com.algalopez.tunturi.user.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuth {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private UserRole role;
}
