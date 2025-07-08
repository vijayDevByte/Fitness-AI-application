package com.project.user_service.dto;

import java.time.LocalDateTime;

import com.project.user_service.model.UserModel.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must have atleast 8 characters")
    private String password;

    private String userName;

    private UserRole userRole;

    private LocalDateTime createdAt;

    private LocalDateTime UpdatedAt;

}
