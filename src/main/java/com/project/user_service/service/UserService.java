package com.project.user_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.project.user_service.dto.RegisterRequest;
import com.project.user_service.dto.UserResponse;
import com.project.user_service.exception.UserAlreadyExistException;
import com.project.user_service.exception.UserNotFoundException;
import com.project.user_service.model.UserModel;
import com.project.user_service.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserResponse registerUser(RegisterRequest userData) {
        Optional<UserModel> isUserExist = userRepo.findByEmail(userData.getEmail());
        // System.out.println(" Check user availabiity:" + isUserExist != null);
        userRepo.existsByEmail(userData.getEmail());
        if (isUserExist.isPresent()) {
            throw new UserAlreadyExistException(userData.getEmail() + " user already exist");
        }
        UserModel user = new UserModel();

        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setUserName(userData.getUserName());
        user.setUserRole(userData.getUserRole() != null ? userData.getUserRole() : UserModel.UserRole.USER);

        UserModel savedUser = userRepo.save(user);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setUserName(savedUser.getUserName());
        response.setUserRole(user.getUserRole().name());
        response.setToken("hsdvfjsdbfhgvasdvafshvksfvv");

        return response;
    }

    public UserResponse findUserById(String id) {
        UserModel user = userRepo.findById(id)
                .orElseThrow((() -> new UserNotFoundException(" User Not Found with " + id)));
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setUserName(user.getUserName());
        return response;
    }

    public UserResponse findUserByEmail(String email) {
        System.out.println(userRepo.findByEmail(email));
        UserModel user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found" + email));

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setUserName(user.getUserName());
        response.setUserRole(user.getUserRole().name());

        return response;

    }

    public void deleteUserById(String id) {

        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("user not found with" + id);
        }
        userRepo.deleteById(id);

    }

    public List<UserResponse> findAllUsers() {
        List<UserModel> users = userRepo.findAll();
        System.out.println(users);

        List<UserResponse> allUsers = users.stream().map(user -> {
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setEmail(user.getEmail());
            response.setUserName(user.getUserName());
            response.setUserRole(user.getUserRole().name());
            return response;
        }).collect(Collectors.toList());

        return allUsers;
    }

    public Boolean isUserExist(String userId) {
        log.info("calling User Validation for userId {userid}", userId);
        return userRepo.existsById(userId);
    }

}
