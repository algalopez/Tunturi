package com.algalopez.tunturi.user.data;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class UserAuth {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private String role;
}
