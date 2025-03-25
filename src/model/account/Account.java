package model.account;

import model.account.enums.AccountType;
import model.interfaces.IAccountBase;
import model.interfaces.IAccountOperation;
import model.transaction.Transaction;
import model.transaction.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements IAccountBase, IAccountOperation {

    private final String accountNumber;
    private final String accountHolderName;
    private final LocalDateTime creationDate;
    private final AccountType accountType;
    private double balance;
    private boolean active;
    private List<Transaction> transactions;


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
        return "";
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public String getAccountHolderName() {
        return "";
    }

    @Override
    public LocalDateTime getCreation() {
        return null;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void deposit(double amount) {
        validateAmount(amount);
        validateAccountActive();

        this.balance += amount;
        addTransaction(new Transaction(TransactionType.DEPOSIT, amount, this.balance));
    }

    @Override
    public void withdraw(double amount) {
        validateAmount(amount);
        validateAccountActive();
        validateSufficientBalance(amount);

        this.balance -= amount;
        addTransaction(new Transaction(TransactionType.WITHDRAW, amount, this.balance));
    }

    @Override
    public void transfer(IAccountBase targetAccount, double amount) {
        validateAmount(amount);
        validateAccountActive();
        validateSufficientBalance(amount);

        if (!(targetAccount instanceof Account)) {
            throw new IllegalArgumentException("Geçersiz Hedef Hesabı");
        }

        Account target = (Account) targetAccount;
        if (!target.isActive()) {
            throw new IllegalStateException("Hedef Hesap Aktif Bir Hesap Değildir");
        }

        this.balance -= amount;
        target.deposit(amount);
        addTransaction(new Transaction(TransactionType.TRANSFER, amount, this.balance));
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Tutar Pozitif Olmalıdır");
        }
    }

    private void validateAccountActive() {
        if (!this.active) {
            throw new IllegalStateException("Hesap Aktif Değil");
        }
    }

    private void validateSufficientBalance(double amount) {
        if (this.balance < amount) {
            throw new IllegalStateException("Yetersiz Bakiye");
        }
    }

    private void addTransaction(Transaction transaction) {
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
