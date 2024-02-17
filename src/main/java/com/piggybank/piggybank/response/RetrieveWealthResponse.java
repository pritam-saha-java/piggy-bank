package com.piggybank.piggybank.response;

import com.piggybank.piggybank.entity.WealthEntity;
import lombok.Data;

@Data
public class RetrieveWealthResponse {

    private WealthEntity wealth;
}
