package com.piggybank.piggybank.service.implementation;

import com.mysql.cj.util.StringUtils;
import com.piggybank.piggybank.configuration.Constants;
import com.piggybank.piggybank.entity.UserEntity;
import com.piggybank.piggybank.exception.BadCredentialsException;
import com.piggybank.piggybank.exception.BadRequestException;
import com.piggybank.piggybank.exception.UserNotFoundException;
import com.piggybank.piggybank.repository.UserRepository;
import com.piggybank.piggybank.request.CreateUserRequest;
import com.piggybank.piggybank.response.CreateUserResponse;
import com.piggybank.piggybank.service.UserService;
import com.piggybank.piggybank.service.WealthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final WealthService wealthService;

    public UserServiceImpl(UserRepository repository,
                           PasswordEncoder passwordEncoder,
                           WealthService wealthService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.wealthService = wealthService;
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

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        //validate user input
        if (StringUtils.isNullOrEmpty(request.getUsername())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDUSERNAME);

        } else if (StringUtils.isNullOrEmpty(request.getPassword())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDPASSWORD);

        } else if (StringUtils.isNullOrEmpty(request.getPassword()) || !Pattern.matches("[0-9]+", request.getTcno())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDTCNO);
        }

        boolean isUsernameExist = isUsernameExist(request.getUsername());
        if (isUsernameExist) {
            throw new BadCredentialsException(Constants.MESSAGE_SAMEUSERNAMEEXIST);
        }

        boolean isTcnoExist = isTcnoExist(request.getTcno());
        if (isTcnoExist) {
            throw new BadCredentialsException(Constants.MESSAGE_SAMETCNOEXIST);
        }

        //Create user by saving the user in database
        UserEntity user = createNewUser(new UserEntity(request.getUsername(), request.getPassword(), request.getTcno()));
        wealthService.newWealthRecord(user.getId());

        CreateUserResponse response = new CreateUserResponse();
        response.setUsername(user.getUsername());
        response.setTcno(user.getTcno());
        return response;

    }
}
