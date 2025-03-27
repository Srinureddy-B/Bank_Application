package controller;

import exception.InvalidAccountException;
import model.transaction.Transaction;
import model.transaction.enums.TransactionType;
import service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public boolean recordTransaction(Transaction transaction) {
        try {
            transactionService.recordTransaction(transaction);
            return true;
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }


    public List<Transaction> getAccountTransactions(String accountNumber) {
        try {
            return transactionService.getAccountTransactions(accountNumber);
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public List<Transaction> getTransactionsAfter(LocalDateTime date) {
        try {
            return transactionService.getTransactionsAfter(date);
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public List<Transaction> getFailedTransactions() {
        return transactionService.getFailedTransactions();
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        try {
            return transactionService.getTransactionsByType(type);
        } catch (InvalidAccountException e) {
            System.out.println("Hata: " + e.getMessage());
            return null;
        }
    }

    public Map<LocalDate, Double> getDailyTransactionSummary() {
        return transactionService.getDailyTransactionSummary();
    }

}
