package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repositories.AccountRepository;
import com.eteration.simplebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionStatus credit(Account account, DepositTransaction depositTransaction) throws InsufficientBalanceException {
        account.post(depositTransaction);
        Transaction transaction = new WithdrawalTransaction(depositTransaction.getAmount());
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return new TransactionStatus("OK", transaction.getApprovalCode());
    }

    @Override
    public TransactionStatus debit(Account account, WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        account.post(withdrawalTransaction);
        Transaction transaction = new WithdrawalTransaction(withdrawalTransaction.getAmount());
        transaction.setAccount(account);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return new TransactionStatus("OK", transaction.getApprovalCode());

    }
}
