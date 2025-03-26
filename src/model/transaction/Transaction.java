package model.transaction;

import model.transaction.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final UUID transactionId;
    private final LocalDateTime date;
    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;
    private final String accountNumber;
    private String targetAccountNumber;
    private String description;
    private boolean isSuccessful;

    public Transaction(TransactionType type, double amount, double balanceAfter, String accountNumber) {
        this.transactionId = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.accountNumber = accountNumber;
        this.isSuccessful = true;
    }

    public UUID getTransactionId() {
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;
        return transactionId.equals(that.transactionId);
    }

    @Override
    public int hashCode() {
        return transactionId.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction{")
                .append("id=").append(transactionId)
                .append(", date=").append(date)
                .append(", type=").append(type.getDescription())
                .append(", amount=").append(amount)
                .append(", balanceAfter=").append(balanceAfter)
                .append(", account=").append(accountNumber);

        if (targetAccountNumber != null) {
            sb.append(", targetAccount=").append(targetAccountNumber);
        }

        if (description != null) {
            sb.append(", description='").append(description).append('\'');
        }

        sb.append(", successful=").append(isSuccessful)
                .append('}');

        return sb.toString();
    }
}