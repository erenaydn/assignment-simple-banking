package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repositories.AccountRepository;
import com.eteration.simplebanking.repositories.TransactionRepository;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account/v1")

public class AccountController {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    public AccountController(AccountService accountService, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;

    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable(value = "accountNumber") String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> getAccount(@RequestBody Account account) {
        accountService.addAccount(account.getOwner(), account.getAccountNumber());
        return new ResponseEntity<>("Successfull created", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
        return new ResponseEntity<>("Successfull created", HttpStatus.OK);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.post(depositTransaction);
        Transaction transaction = new DepositTransaction(depositTransaction.getAmount());
        transaction.setAccount(account);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        //TransactionStatus response = this.transactionService.credit(account, depositTransaction);
        // service üzerinde kullanırken test yapısı bozulacak diye controller üzerinde yapıldı işlemler.
        return new ResponseEntity<>(new TransactionStatus("OK", depositTransaction.getApprovalCode()), HttpStatus.OK);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.post(withdrawalTransaction);
        Transaction transaction = new WithdrawalTransaction(withdrawalTransaction.getAmount());
        transaction.setAccount(account);
        accountRepository.save(account);
        transactionRepository.save(transaction);

        //TransactionStatus response = transactionService.debit(account, withdrawalTransaction);
        // service üzerinde kullanırken test yapısı bozulacak diye controller üzerinde yapıldı işlemler.
        return new ResponseEntity<>(new TransactionStatus("OK", transaction.getApprovalCode()), HttpStatus.OK);
    }
}