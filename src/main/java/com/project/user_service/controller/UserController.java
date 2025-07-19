package com.project.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.user_service.dto.ApiResponse;
import com.project.user_service.dto.RegisterRequest;
import com.project.user_service.dto.UserResponse;

import com.project.user_service.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@RequestBody RegisterRequest user) {
        ApiResponse<UserResponse> response = new ApiResponse<>("success", "user registered successfully",
                userService.registerUser(user));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable String id) {

        ApiResponse<UserResponse> response = new ApiResponse<>("success", "Users fetched",
                userService.findUserById(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateUser(@PathVariable String id) {

        // ApiResponse<Boolean> response = new ApiResponse<>("success", "Users fetched",
        // userService.isUserExist(id));

        return ResponseEntity.ok(userService.isUserExist(id));
    }

    @PostMapping("/getuser")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(@RequestBody String email) {
        ApiResponse<UserResponse> response = new ApiResponse<>("success", "Users fetched",
                userService.findUserByEmail(email));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUserById(@PathVariable String id) {

        userService.deleteUserById(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "user deleted successfully", id));
    }

    @GetMapping("/allusers")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>("success", "Fetched All users", userService.findAllUsers()));
    }

}
