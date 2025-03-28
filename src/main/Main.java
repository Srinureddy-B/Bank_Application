package main;

import controller.AccountController;
import controller.CustomerController;
import controller.TransactionController;
import service.AccountService;
import service.CustomerService;
import service.TransactionService;
import view.BankMenu;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();

        CustomerController customerController = new CustomerController(customerService);
        AccountController accountController = new AccountController(accountService);
        TransactionController transactionController = new TransactionController(transactionService);

        BankMenu bankMenu = new BankMenu(accountController, customerController, transactionController);
        bankMenu.start();
    }
}