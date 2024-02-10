package com.eteration.simplebanking.repositories;

import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
