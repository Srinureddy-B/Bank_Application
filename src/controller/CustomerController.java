package controller;

import exception.*;
import model.account.Account;
import model.account.enums.AccountType;
import model.customer.Customer;
import service.CustomerService;

import java.util.List;
import java.util.UUID;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean registerCustomer(Customer customer) {
        try {
            customerService.registerCustomer(customer);
            return true;
        } catch (InvalidCustomerIdException | InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public List<Account> getCustomerAccounts(UUID customerId) {
        try {
            return customerService.getCustomerAccounts(customerId);
        } catch (InvalidCustomerIdException | InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public boolean addAccountToCustomer(UUID customerId, Account account) {
        try {
            customerService.addAccountToCustomer(customerId, account);
            return true;
        } catch (InvalidCustomerIdException | InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public double getCustomerTotalBalance(UUID customerId) {
        try {
            return customerService.getCustomerTotalBalance(customerId);
        } catch (InvalidCustomerIdException | InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return 0.0;
        }
    }

    public List<Account> getCustomerAccountsByType(UUID customerId, AccountType type) {
        try {
            return customerService.getCustomerAccountsByType(customerId, type);
        } catch (InvalidCustomerIdException | InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public boolean updateCustomerInfo(UUID customerId, Customer updatedCustomer) {
        try {
            customerService.updateCustomerInfo(customerId, updatedCustomer);
            return true;
        } catch (InvalidCustomerIdException | InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public boolean closeCustomerAccount(UUID customerId, String accountNumber) {
        try {
            customerService.closeCustomerAccount(customerId, accountNumber);
            return true;
        } catch (InvalidCustomerIdException | InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }
}