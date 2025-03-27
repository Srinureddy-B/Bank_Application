package service;

import exception.*;
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
        if (accountMap.containsKey(accountNumber)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
        }

        Account account = new CheckingAccount(accountNumber, accountHolderName);
        accountMap.put(accountNumber,account);
        return account;
    }

    public Account createSavingsAccount(String accountNumber, String accountHolderName) {
        if (accountMap.containsKey(accountNumber)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
        }

        Account account = new CheckingAccount(accountNumber, accountHolderName);
        accountMap.put(accountNumber,account);
        return account;
    }

    public Account getAccount(String accountNumber) {
        Account account = accountMap.get(accountNumber);
        if (account == null) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        return account;
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountMap.values());
    }

    public List<Account> getActiveAccounts() {
        return accountMap.values().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }

    public void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(ErrorCode.INVALID_AMOUNT.getMessage());
        }
        Account account = getAccount(accountNumber);
        if (!account.isActive()) {
            throw new AccountInactiveException(ErrorCode.ACCOUNT_INACTIVE.getMessage());
        }
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(ErrorCode.INVALID_AMOUNT.getMessage());
        }
        Account account = getAccount(accountNumber);
        if (!account.isActive()) {
            throw new AccountInactiveException(ErrorCode.ACCOUNT_INACTIVE.getMessage());
        }
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException(ErrorCode.INSUFFICIENT_BALANCE.getMessage());
        }
        account.withdraw(amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(ErrorCode.INVALID_AMOUNT.getMessage());
        }
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);

        if (!fromAccount.isActive()) {
            throw new AccountInactiveException(ErrorCode.ACCOUNT_INACTIVE.getMessage());
        }
        if (!toAccount.isActive()) {
            throw new AccountInactiveException(ErrorCode.TARGET_ACCOUNT_INACTIVE.getMessage());
        }
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException(ErrorCode.INSUFFICIENT_BALANCE.getMessage());
        }

        fromAccount.transfer(toAccount, amount);
    }

    public void activateAccount(String accountNumber) {
        Account account = getAccount(accountNumber);
        if (account.isActive()) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_ALREADY_ACTIVE.getMessage());
        }
        account.activate();
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
