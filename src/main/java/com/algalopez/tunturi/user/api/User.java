package com.algalopez.tunturi.user.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private String role;
    private String email;
    private Integer level;
}
