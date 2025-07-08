package com.project.user_service.dto;

import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String userName;

    private String email;
    private String userRole;
    private String token;

}
