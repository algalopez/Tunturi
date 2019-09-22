package com.algalopez.ranking.user.data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserInfo {

    private Long id;
    private String email;
    private String username;
    private UserInfoLevel level;
}
