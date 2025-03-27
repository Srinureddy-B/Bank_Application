package controller;

import exception.AccountInactiveException;
import exception.InsufficientBalanceException;
import exception.InvalidAccountException;
import exception.InvalidAmountException;
import model.account.Account;
import model.account.enums.AccountType;
import service.AccountService;

import java.util.List;
import java.util.Map;

public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account createCheckingAccount(String accountNumber, String accountHolderName) {
        try {
            return accountService.createCheckingAccount(accountNumber, accountHolderName);
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public Account createSavingsAccount(String accountNumber, String accountHolderName) {
        try {
            return accountService.createSavingsAccount(accountNumber, accountHolderName);
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public Account getAccount(String accountNumber) {
        try {
            return accountService.getAccount(accountNumber);
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    public List<Account> getActiveAccounts() {
        return accountService.getActiveAccounts();
    }

    public boolean deposit(String accountNumber, double amount) {
        try {
            accountService.deposit(accountNumber, amount);
            return true;
        } catch (InvalidAmountException | AccountInactiveException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public boolean withdraw(String accountNumber, double amount) {
        try {
            accountService.withdraw(accountNumber, amount);
            return true;
        } catch (InvalidAmountException | AccountInactiveException | InsufficientBalanceException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        try {
            accountService.transfer(fromAccountNumber, toAccountNumber, amount);
            return true;
        } catch (InvalidAmountException | AccountInactiveException | InsufficientBalanceException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public boolean activateAccount(String accountNumber) {
        try {
            accountService.activateAccount(accountNumber);
            return true;
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public boolean deactivateAccount(String accountNumber) {
        try {
            accountService.deactivateAccount(accountNumber);
            return true;
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public double getTotalBalance() {
        return accountService.getTotalBalance();
    }

    public Map<AccountType, Double> getAverageBalanceByAccountType() {
        return accountService.getAverageBalanceByAccountType();
    }
}
