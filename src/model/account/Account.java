package model.account;

import exception.AccountInactiveException;
import exception.InsufficientBalanceException;
import exception.InvalidAccountException;
import exception.InvalidAmountException;
import model.account.enums.AccountType;
import model.interfaces.IAccountBase;
import model.interfaces.IAccountOperation;
import model.transaction.Transaction;
import model.transaction.builder.TransactionBuilder;
import model.transaction.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account implements IAccountBase, IAccountOperation {

    private final String accountNumber;
    private final String accountHolderName;
    private final LocalDateTime creationDate;
    private final AccountType accountType;
    private double balance;
    private boolean active;
    private List<Transaction> transactions;

    private static final double MAX_TRANSACTION_AMOUNT = 100_000.0;
    private static final double MIN_TRANSACTION_AMOUNT = 1.0;


    protected Account(String accountNumber, String accountHolderName, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.creationDate = LocalDateTime.now();
        this.balance = 0.0;
        this.active = true;
        this.transactions = new ArrayList<>();
    }



    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountHolderName() {
        return accountHolderName;
    }

    @Override
    public LocalDateTime getCreation() {
        return creationDate;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void deposit(double amount) {
        validateAmount(amount);
        validateAccountActive();

        this.balance += amount;
        addTransaction(TransactionType.DEPOSIT, amount, this.balance);
    }

    @Override
    public void withdraw(double amount) {
        validateAmount(amount);
        validateAccountActive();
        validateSufficientBalance(amount);

        this.balance -= amount;
        addTransaction(TransactionType.WITHDRAW, amount, this.balance);
    }

    @Override
    public void transfer(IAccountBase targetAccount, double amount) {
        validateAmount(amount);
        validateAccountActive();
        validateSufficientBalance(amount);

        if (!(targetAccount instanceof Account target)) {
            throw new InvalidAccountException("Geçersiz Hedef Hesabı");
        }

        if (!target.isActive()) {
            throw new AccountInactiveException("Hedef Hesap Aktif Bir Hesap Değildir");
        }

        this.balance -= amount;
        target.deposit(amount);

        Transaction transaction = new TransactionBuilder(TransactionType.TRANSFER, amount, this.balance, this.accountNumber)
                .targetAccountNumber(target.getAccountNumber())
                .description("Para Transferi İşlemi")
                .build();
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactions);
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Tutar Pozitif Olmalıdır");
        }
        if (amount < MIN_TRANSACTION_AMOUNT) {
            throw new InvalidAmountException("İşlem tutarı minimum " + MIN_TRANSACTION_AMOUNT + " olmalıdır");
        }
        if (amount > MAX_TRANSACTION_AMOUNT) {
            throw new InvalidAmountException("İşlem tutarı maksimum " + MAX_TRANSACTION_AMOUNT + " olmalıdır");
        }
    }

    private void validateAccountActive() {
        if (!this.active) {
            throw new AccountInactiveException("Hesap Aktif Değil");
        }
    }

    private void validateSufficientBalance(double amount) {
        if (this.balance < amount) {
            throw new InsufficientBalanceException("Yetersiz Bakiye");
        }
    }

    private void addTransaction(TransactionType type, double amount, double balanceAfter) {
        Transaction transaction = new TransactionBuilder(type, amount, balanceAfter, this.accountNumber)
                .description(type.getDescription())
                .build();
        transactions.add(transaction);
    }
    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public abstract double getMaxWithdrawLimit();
}
