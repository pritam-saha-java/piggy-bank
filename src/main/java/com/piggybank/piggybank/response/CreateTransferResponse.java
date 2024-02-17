package com.piggybank.piggybank.response;

import com.piggybank.piggybank.entity.TransferEntity;
import lombok.Data;

@Data
public class CreateTransferResponse {

    private TransferEntity transfer;
}
