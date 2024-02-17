package com.piggybank.piggybank.service.implementation;

import com.piggybank.piggybank.entity.UserEntity;
import com.piggybank.piggybank.exception.UserNotFoundException;
import com.piggybank.piggybank.repository.UserRepository;
import com.piggybank.piggybank.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository,
                           PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public UserEntity createNewUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public UserEntity findByUserName(String username) {
        UserEntity user = repository.findByUsername(username);

        if (user == null)
            throw new UserNotFoundException(username);
        else
            return user;
    }

    @Override
    public UserEntity findByTcno(String tcno) {
        UserEntity user = repository.findByTcno(tcno);

        if (user == null)
            throw new UserNotFoundException("with TC No: " + tcno);
        else
            return user;
    }

    @Override
    public Optional<UserEntity> loadUserByUsername(String username) {
        UserEntity user = repository.findByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean isUsernameExist(String username) {
        UserEntity user = repository.findByUsername(username);
        return user != null;
    }

    @Override
    public boolean isTcnoExist(String tcno) {
        UserEntity user = repository.findByTcno(tcno);
        return user != null;
    }
}
