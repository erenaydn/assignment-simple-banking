package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;

public interface AccountService {

    Account findAccount(String accountNumber);

    void addAccount(String owner, String accountNumber);

    void updateAccount(Account account);

}
