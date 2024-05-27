package com.aninfo.model;

import com.aninfo.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cbu;

    private Double balance;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval=true)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(Double balance) {
        this.balance = balance;
    }

    public Account(Double balance, List<Transaction> transactions) {
        this.balance = balance;
        this.transactions.addAll(transactions);
    }

    public void addWithdraw(Transaction transaction) {
        updateBalance(transaction);
        this.transactions.add(transaction);
    }

    public void addDeposit(Transaction transaction) {
        updateBalance(transaction);
        this.transactions.add(transaction);
    }

    public void updateBalance(Transaction transaction) {
        if (TransactionType.WITHDRAW.equals(transaction.getType())) {
            this.balance -= transaction.getAmount();
        } else {
            this.balance += transaction.getAmount();
        }
    }
}