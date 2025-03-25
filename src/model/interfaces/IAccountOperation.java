package model.interfaces;

import model.transaction.Transaction;

import java.util.List;

public interface IAccountOperation {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(IAccountBase targetAccount, double amount);
    List<Transaction> getTransactionHistory();
}
