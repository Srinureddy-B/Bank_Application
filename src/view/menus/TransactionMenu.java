package view.menus;

import controller.TransactionController;
import model.transaction.Transaction;
import model.transaction.enums.TransactionType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TransactionMenu {
    private final Scanner scanner;
    private final TransactionController transactionController;

    public TransactionMenu(Scanner scanner, TransactionController transactionController) {
        this.scanner = scanner;
        this.transactionController = transactionController;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== İŞLEM İŞLEMLERİ ===");
            System.out.println("1. Hesap İşlemlerini Görüntüle");
            System.out.println("2. Başarısız İşlemleri Listele");
            System.out.println("3. İşlem Tipine Göre Filtrele");
            System.out.println("4. Belirli Tarihten Sonraki İşlemler");
            System.out.println("0. Ana Menüye Dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAccountTransactions();
                    break;
                case 2:
                    displayFailedTransactions();
                    break;
                case 3:
                    filterTransactionsByType();
                    break;
                case 4:
                    displayTransactionsAfterDate();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private void displayAccountTransactions() {
        System.out.println("\n=== HESAP İŞLEMLERİ ===");
        System.out.print("Hesap Numarası: ");
        String accountNumber = scanner.nextLine();

        List<Transaction> transactions = transactionController.getAccountTransactions(accountNumber);
        if (transactions != null && !transactions.isEmpty()) {
            System.out.println("\nİşlem Geçmişi:");
            transactions.forEach(transaction -> {
                System.out.println("İşlem ID: " + transaction.getTransactionId());
                System.out.println("Tarih: " + transaction.getDate());
                System.out.println("Tip: " + transaction.getType().getDescription());
                System.out.println("Miktar: " + transaction.getAmount());
                System.out.println("Bakiye: " + transaction.getBalanceAfter());
                if (transaction.getTargetAccountNumber() != null) {
                    System.out.println("Hedef Hesap: " + transaction.getTargetAccountNumber());
                }
                if (transaction.getDescription() != null) {
                    System.out.println("Açıklama: " + transaction.getDescription());
                }
                System.out.println("------------------------");
            });
        } else {
            System.out.println("İşlem geçmişi bulunamadı!");
        }
    }

    private void displayFailedTransactions() {
        System.out.println("\n=== BAŞARISIZ İŞLEMLER ===");
        List<Transaction> failedTransactions = transactionController.getFailedTransactions();

        if (failedTransactions != null && !failedTransactions.isEmpty()) {
            System.out.println("\nBaşarısız İşlemler:");
            failedTransactions.forEach(transaction -> {
                System.out.println("İşlem ID: " + transaction.getTransactionId());
                System.out.println("Tarih: " + transaction.getDate());
                System.out.println("Tip: " + transaction.getType().getDescription());
                System.out.println("Miktar: " + transaction.getAmount());
                System.out.println("Hesap: " + transaction.getAccountNumber());
                System.out.println("------------------------");
            });
        } else {
            System.out.println("Başarısız işlem bulunamadı!");
        }
    }

    private void filterTransactionsByType() {
        System.out.println("\n=== İŞLEM TİPİNE GÖRE FİLTRELEME ===");
        System.out.println("\nİşlem Tipi:");
        System.out.println("1. Para Yatırma");
        System.out.println("2. Para Çekme");
        System.out.println("3. Transfer");
        System.out.print("Seçiminiz: ");

        TransactionType transactionType;
        switch (scanner.nextInt()) {
            case 1:
                transactionType = TransactionType.DEPOSIT;
                break;
            case 2:
                transactionType = TransactionType.WITHDRAW;
                break;
            case 3:
                transactionType = TransactionType.TRANSFER;
                break;
            default:
                System.out.println("Geçersiz işlem tipi!");
                return;
        }
        scanner.nextLine();

        List<Transaction> filteredTransactions = transactionController.getTransactionsByType(transactionType);
        if (filteredTransactions != null && !filteredTransactions.isEmpty()) {
            System.out.println("\nFiltrelenmiş İşlemler:");
            filteredTransactions.forEach(transaction -> {
                System.out.println("İşlem ID: " + transaction.getTransactionId());
                System.out.println("Tarih: " + transaction.getDate());
                System.out.println("Miktar: " + transaction.getAmount());
                System.out.println("Hesap: " + transaction.getAccountNumber());
                System.out.println("------------------------");
            });
        } else {
            System.out.println("Bu tipte işlem bulunamadı!");
        }
    }

    private void displayTransactionsAfterDate() {
        System.out.println("\n=== BELİRLİ TARİHTEN SONRAKİ İŞLEMLER ===");
        System.out.print("Tarih (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();

        try {
            LocalDateTime date = LocalDateTime.parse(dateStr + "T00:00:00");
            List<Transaction> transactions = transactionController.getTransactionsAfter(date);

            if (transactions != null && !transactions.isEmpty()) {
                System.out.println("\nİşlemler:");
                transactions.forEach(transaction -> {
                    System.out.println("İşlem ID: " + transaction.getTransactionId());
                    System.out.println("Tarih: " + transaction.getDate());
                    System.out.println("Tip: " + transaction.getType().getDescription());
                    System.out.println("Miktar: " + transaction.getAmount());
                    System.out.println("Hesap: " + transaction.getAccountNumber());
                    System.out.println("------------------------");
                });
            } else {
                System.out.println("Bu tarihten sonra işlem bulunamadı!");
            }
        } catch (Exception e) {
            System.out.println("Geçersiz tarih formatı!");
        }
    }
}