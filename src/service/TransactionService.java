package service;

import model.transaction.Transaction;
import model.transaction.enums.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionService {
    private final Map<String, List<Transaction>> accountTransactions;

    public TransactionService() {
        this.accountTransactions = new HashMap<>();
    }

    public void recordTransaction(Transaction transaction) {
        String accountNumber = transaction.getAccountNumber();
        accountTransactions.computeIfAbsent(accountNumber, k -> new ArrayList<>())
                .add(transaction);
    }

    public List<Transaction> getAccountTransactions(String accountNumber) {
        return accountTransactions.getOrDefault(accountNumber, new ArrayList<>());
    }

    public List<Transaction> getTransactionsAfter(LocalDateTime date) {
        return accountTransactions.values().stream()
                .flatMap(List::stream)
                .filter(t -> t.getDate().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Transaction> getFailedTransactions() {
        return accountTransactions.values().stream()
                .flatMap(List::stream)
                .filter(t -> !t.isSuccessful())
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        return accountTransactions.values().stream()
                .flatMap(List::stream)
                .filter(t -> t.getType() == type)
                .collect(Collectors.toList());
    }

    public Map<LocalDate, Double> getDailyTransactionSummary() {
        return accountTransactions.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        t -> t.getDate().toLocalDate(),
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }
}