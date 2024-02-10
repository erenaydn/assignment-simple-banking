package com.eteration.simplebanking.repositories;

import com.eteration.simplebanking.model.WithdrawalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithDrawalRepository extends JpaRepository<WithdrawalTransaction, Long> {
}
