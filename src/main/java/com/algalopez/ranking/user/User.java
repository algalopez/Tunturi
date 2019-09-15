package com.algalopez.ranking.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    private Long id;
    private String email;
    private String username;
    private UserLevel level;
}
