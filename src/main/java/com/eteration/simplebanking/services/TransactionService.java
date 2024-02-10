package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

public interface TransactionService {

    TransactionStatus credit(Account account, DepositTransaction transaction) throws InsufficientBalanceException;

    TransactionStatus debit(Account account, WithdrawalTransaction transaction) throws InsufficientBalanceException;

}
