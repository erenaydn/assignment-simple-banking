package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repositories.AccountRepository;
import com.eteration.simplebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

// This class is a place holder you can change the complete implementation
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            account = makeMockedData();
        }
        return account;
    }

    public void addAccount(String owner, String accountNumber) {
        Account account = new Account(owner, accountNumber);
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public Account makeMockedData() {
        Account mockData = new Account();

        mockData.setAccountNumber("669-7788");
        mockData.setOwner("Kerem Karaca");
        mockData.setBalance(950.0);

        long epochMilli = 1585203350550L; // "2020-03-26T06:15:50.550+0000"
        Date date = new Date(epochMilli);
        mockData.setCreateDate(date);

        Transaction deposit = new DepositTransaction(1000.0);
        deposit.setAccount(mockData);
        deposit.setApprovalCode("67f1aada-637d-4469-a650-3fb6352527ba");
        deposit.setType("DepositTransaction");
        long newEpochMilli = 1585203363563L; //"2020-03-26T06:16:03.563+0000"
        Date depostDate = new Date(newEpochMilli);
        deposit.setDate(depostDate);
        mockData.getTransactions().add(deposit);

        Transaction withdrawal = new WithdrawalTransaction(50.0);
        withdrawal.setAccount(mockData);
        withdrawal.setApprovalCode("a66cce54-335b-4e46-9b49-05017c4b38dd");
        withdrawal.setType("WithdrawalTransaction");
        long withdrawalEpochMilli = 1585203395047L; //"2020-03-26T06:16:35.047+0000"
        Date withdrawalDate = new Date(withdrawalEpochMilli);
        deposit.setDate(withdrawalDate);
        mockData.getTransactions().add(withdrawal);


        return mockData;
    }

}
