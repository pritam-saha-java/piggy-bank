package com.piggybank.piggybank.service;

import com.piggybank.piggybank.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity findByUserName(String username);

    UserEntity findByTcno(String tcno);

    UserEntity createNewUser(UserEntity user);

    boolean isUsernameExist(String username);

    boolean isTcnoExist(String tcno);
}
