package com.eteration.simplebanking.repositories;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountNumber(String accountNumber);

}
