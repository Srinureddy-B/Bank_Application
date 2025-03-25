package model.transaction;

import model.transaction.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final LocalDateTime date;
    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;


    public Transaction(TransactionType type, double amount, double balanceAfter) {
        this.transactionId = generateTransactionId();
        this.date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    private String generateTransactionId() {
        return "TRX-" + System.currentTimeMillis();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", date=" + date +
                ", type=" + type +
                ", amount=" + amount +
                ", balanceAfter=" + balanceAfter +
                '}';
    }
}
