package com.aninfo.enums;

import com.aninfo.handlers.InvalidTransactionTypeException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum TransactionType {
    DEPOSIT,
    WITHDRAW;

    public TransactionType revertType() {
        switch (this){
            case DEPOSIT:
                return WITHDRAW;
            case WITHDRAW:
                return DEPOSIT;
            default:
                throw new InvalidTransactionTypeException("Invalid transaction type");
        }
    }
}