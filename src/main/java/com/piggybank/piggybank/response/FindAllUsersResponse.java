package com.piggybank.piggybank.response;

import com.piggybank.piggybank.entity.UserEntity;
import lombok.Data;

import java.util.List;
@Data
public class FindAllUsersResponse {

    List<UserEntity> userList;
}
