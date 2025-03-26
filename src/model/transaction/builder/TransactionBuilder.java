package model.transaction.builder;

import model.transaction.Transaction;
import model.transaction.enums.TransactionType;

public class TransactionBuilder {
    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;
    private final String accountNumber;
    private String targetAccountNumber;
    private String description;
    private boolean isSuccessful = true;

    public TransactionBuilder(TransactionType type, double amount, double balanceAfter, String accountNumber) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.accountNumber = accountNumber;
    }

    public TransactionBuilder targetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
        return this;
    }

    public TransactionBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TransactionBuilder successful(boolean successful) {
        this.isSuccessful = successful;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction(type, amount, balanceAfter, accountNumber);
        transaction.setTargetAccountNumber(targetAccountNumber);
        transaction.setDescription(description);
        transaction.setSuccessful(isSuccessful);
        return transaction;
    }
}