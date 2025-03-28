package view.menus;

import controller.AccountController;
import controller.TransactionController;
import model.account.enums.AccountType;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class ReportMenu {
    private final Scanner scanner;
    private final AccountController accountController;
    private final TransactionController transactionController;

    public ReportMenu(Scanner scanner, AccountController accountController, TransactionController transactionController) {
        this.scanner = scanner;
        this.accountController = accountController;
        this.transactionController = transactionController;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== RAPORLAR ===");
            System.out.println("1. Günlük İşlem Özeti");
            System.out.println("2. Toplam Bakiye");
            System.out.println("3. Hesap Tipine Göre Ortalama Bakiye");
            System.out.println("0. Ana Menüye Dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayDailyTransactionSummary();
                    break;
                case 2:
                    displayTotalBalance();
                    break;
                case 3:
                    displayAverageBalanceByAccountType();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private void displayDailyTransactionSummary() {
        System.out.println("\n=== GÜNLÜK İŞLEM ÖZETİ ===");
        Map<LocalDate, Double> summary = transactionController.getDailyTransactionSummary();

        if (summary != null && !summary.isEmpty()) {
            System.out.println("\nGünlük İşlem Özeti:");
            summary.forEach((date, amount) ->
                    System.out.println(date + ": " + amount + " TL")
            );
        } else {
            System.out.println("İşlem özeti bulunamadı!");
        }
    }

    private void displayTotalBalance() {
        System.out.println("\n=== TOPLAM BAKİYE ===");
        double totalBalance = accountController.getTotalBalance();
        System.out.println("Toplam Bakiye: " + totalBalance + " TL");
    }

    private void displayAverageBalanceByAccountType() {
        System.out.println("\n=== HESAP TİPİNE GÖRE ORTALAMA BAKİYE ===");
        Map<AccountType, Double> averages = accountController.getAverageBalanceByAccountType();

        if (averages != null && !averages.isEmpty()) {
            System.out.println("\nHesap Tipine Göre Ortalama Bakiye:");
            averages.forEach((type, average) ->
                    System.out.println(type.getDescription() + ": " + average + " TL")
            );
        } else {
            System.out.println("Ortalama bakiye bilgisi bulunamadı!");
        }
    }
}