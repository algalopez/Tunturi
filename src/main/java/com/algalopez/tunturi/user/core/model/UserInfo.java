package com.algalopez.tunturi.user.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

    private Long id;
    private String username;
    private String email;
    private Integer level;

}
