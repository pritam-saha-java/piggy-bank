package com.piggybank.piggybank.service.implementation;

import com.mysql.cj.util.StringUtils;
import com.piggybank.piggybank.configuration.Constants;
import com.piggybank.piggybank.entity.TransferEntity;
import com.piggybank.piggybank.entity.UserEntity;
import com.piggybank.piggybank.exception.BadRequestException;
import com.piggybank.piggybank.exception.TransactionLimitException;
import com.piggybank.piggybank.repository.TransferRepository;
import com.piggybank.piggybank.request.CreateTransferRequest;
import com.piggybank.piggybank.response.CreateTransferResponse;
import com.piggybank.piggybank.service.TransactionService;
import com.piggybank.piggybank.service.TransferService;
import com.piggybank.piggybank.service.UserService;
import com.piggybank.piggybank.service.WealthService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;
    private final UserService userService;
    private final WealthService wealthService;

    public TransferServiceImpl(TransferRepository repository,
                               UserService userService,
                               WealthService wealthService) {
        this.repository = repository;
        this.userService = userService;
        this.wealthService = wealthService;
    }

    @Override
    public TransferEntity createNewTransfer(TransferEntity transfer) {
        return repository.save(transfer);
    }

    @Override
    public List<TransferEntity> findAllTransfersFrom24Hours(Long userId) {
        return repository.findAllTransfersFrom24Hours(userId);
    }

    @Override
    public CreateTransferResponse createTransfer(CreateTransferRequest request){

        //Validate request input
        if (StringUtils.isNullOrEmpty(request.getCurrency())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDCURRENCY);

        } else if (StringUtils.isNullOrEmpty(request.getSenderUsername())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDUSERNAME);

        } else if (StringUtils.isNullOrEmpty(request.getReceiverTcno()) || request.getReceiverTcno().length() != 11) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDUSERNAME);

        } else if (Objects.isNull(request.getAmount() || request.getAmount().signum() == -1) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDAMOUNT);

        }

        Map<String, Double> currencyRates = wealthService.getCurrencyRates();

        BigDecimal singleTransferLimit = new BigDecimal(20000);
        BigDecimal tryEquivalent = getTryEquivalent(currencyRates, request.getCurrency(), request.getAmount());
        if (tryEquivalent.compareTo(singleTransferLimit) == 1) {
            throw new TransactionLimitException(Constants.MESSAGE_EXCEEDEDMAXVALUE);
        }

        UserEntity senderUser = userService.findByUserName(request.getSenderUsername());

        //find all recent transactions
        List<TransferEntity> last24HourTransfers = findAllTransfersFrom24Hours(senderUser.getId());
        checkDailyTransferLimitExceedition(currencyRates, last24HourTransfers, tryEquivalent);

        UserEntity receiverUser = userService.findByTcno(request.getReceiverTcno());

        if (senderUser.equals(receiverUser)) {
            throw new BadRequestException(Constants.MESSAGE_SAMEUSERTRANSACTION);
        }

        wealthService.makeWealthTransaction(senderUser.getId(), request.getCurrency(), request.getAmount(), false);
        wealthService.makeWealthTransaction(receiverUser.getId(), request.getCurrency(), request.getAmount(), true);

        TransferEntity transfer = createNewTransfer(new TransferEntity(senderUser.getId(), receiverUser.getId(),
                request.getCurrency(), request.getAmount()));

        //crate response
        CreateTransferResponse response = new CreateTransferResponse();
        response.setTransfer(transfer);
        return response;

    }

    private BigDecimal getTryEquivalent(Map<String, Double> currencyRates,
                                        String currency,
                                        BigDecimal amount) {

        BigDecimal transferCurrRate = BigDecimal.valueOf(currencyRates.get(currency));
        return amount.divide(transferCurrRate, 9, RoundingMode.HALF_UP);
    }

    private void checkDailyTransferLimitExceedition(Map<String, Double> currencyRates,
                                                    List<TransferEntity> last24HourTransfers,
                                                    BigDecimal transferTryEquivalent) {

        BigDecimal dailyTransferLimit = new BigDecimal(100000);

        BigDecimal rate;
        BigDecimal tryEquivalent;

        //Check for each transfer limit exceed or not
        for (TransferEntity transfer : last24HourTransfers) {

            rate = BigDecimal.valueOf(currencyRates.get(transfer.getCurrency()));
            tryEquivalent = transfer.getAmount().divide(rate, 9, RoundingMode.HALF_UP);

            transferTryEquivalent = transferTryEquivalent.add(tryEquivalent);

            if (transferTryEquivalent.compareTo(dailyTransferLimit) == 1) {
                throw new TransactionLimitException(Constants.MESSAGE_EXCEEDEDMAXVALUEFORDAY);
            }

        }

    }
}
