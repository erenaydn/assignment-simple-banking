package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue(value = "WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction() {
        // TODO Auto-generated constructor stub
    }

    public WithdrawalTransaction(Double amount) {
        super(amount);
    }

    public WithdrawalTransaction(Integer amount) {
        super(new Date(), Double.valueOf(amount));
    }
}
