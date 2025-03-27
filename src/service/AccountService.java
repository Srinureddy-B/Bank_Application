package service;

import model.account.Account;
import model.account.CheckingAccount;
import model.account.enums.AccountType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountService {
    private final Map<String, Account> accountMap;

    public AccountService() {
        this.accountMap = new HashMap<>();
    }

    public Account createCheckingAccount(String accountNumber, String accountHolderName) {
        Account account = new CheckingAccount(accountNumber, accountHolderName);
        accountMap.put(accountNumber,account);
        return account;
    }

    public Account createSavingsAccount(String accountNumber, String accountHolderName) {
        Account account = new CheckingAccount(accountNumber, accountHolderName);
        accountMap.put(accountNumber,account);
        return account;
    }

    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public List<Account> getAllAcounts() {
        return new ArrayList<>(accountMap.values());
    }

    public List<Account> getActiveAccounts() {
        return accountMap.values().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }

    public void deposit(String accountNumber, double amount) {
        Account account =  getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);

        if (fromAccount != null && toAccount != null) {
            fromAccount.transfer(toAccount, amount);
        }
    }

    public void activateAccount(String accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.activate();
        }
    }

    public void deactivateAccount(String accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deactivate();
        }
    }

    public double getTotalBalance() {
        return accountMap.values().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    public Map<AccountType, Double> getAverageBalanceByAccountType() {
        return accountMap.values().stream()
                .collect(Collectors.groupingBy(
                        Account::getAccountType,
                        Collectors.averagingDouble(Account::getBalance)
                ));
    }
}
