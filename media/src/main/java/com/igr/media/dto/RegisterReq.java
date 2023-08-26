package com.igr.media.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterReq {
     String username;
     String password;
     String Name;
     Role role;
}
