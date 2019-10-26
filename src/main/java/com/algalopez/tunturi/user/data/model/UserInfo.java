package com.algalopez.tunturi.user.data.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserInfo {

    private Long id;
    private String email;
    private String username;
    private Integer level;
}
