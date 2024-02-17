package com.piggybank.piggybank.service;

import com.piggybank.piggybank.entity.UserEntity;
import com.piggybank.piggybank.request.CreateUserRequest;
import com.piggybank.piggybank.response.CreateUserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity findByUserName(String username);

    UserEntity findByTcno(String tcno);

    UserEntity createNewUser(UserEntity user);

    Optional<UserEntity> loadUserByUsername(String username);

    boolean isUsernameExist(String username);

    boolean isTcnoExist(String tcno);

    CreateUserResponse createUser(CreateUserRequest request);
}
