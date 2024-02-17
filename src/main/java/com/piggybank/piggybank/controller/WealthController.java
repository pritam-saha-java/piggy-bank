package com.piggybank.piggybank.controller;

import com.mysql.cj.util.StringUtils;
import com.piggybank.piggybank.configuration.Constants;
import com.piggybank.piggybank.entity.UserEntity;
import com.piggybank.piggybank.entity.WealthEntity;
import com.piggybank.piggybank.exception.BadRequestException;
import com.piggybank.piggybank.request.RetrieveWealthRequest;
import com.piggybank.piggybank.response.RetrieveWealthResponse;
import com.piggybank.piggybank.service.UserService;
import com.piggybank.piggybank.service.WealthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/wealth")
public class WealthController {

    private final WealthService wealthService;
    private final UserService userService;

    public WealthController(WealthService wealthService,
                            UserService userService) {
        this.wealthService = wealthService;
        this.userService = userService;
    }


    @GetMapping(path = "/retrieve", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<RetrieveWealthResponse> retrieveWealth(@RequestBody RetrieveWealthRequest request) {

        if (StringUtils.isNullOrEmpty(request.getUsername()))
            throw new BadRequestException(Constants.MESSAGE_INVALIDUSERNAME);

        //Get user info and wealth info
        UserEntity user = userService.findByUserName(request.getUsername());
        WealthEntity wealth = wealthService.findWealth(user.getId());

        RetrieveWealthResponse response = new RetrieveWealthResponse();
        response.setWealth(wealth);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
