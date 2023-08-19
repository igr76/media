package com.igr.media.dto;

import lombok.Data;

@Data
public class RegisterReq {
    private String username;
    private String password;
    private String Name;
    private Role role;
}
