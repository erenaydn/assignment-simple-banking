package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue(value = "DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction() {
    }

    public DepositTransaction(Double amount) {
        super(amount);
    }

    public DepositTransaction(Integer amount) {
        super(new Date(), Double.valueOf(amount));
    }

}
