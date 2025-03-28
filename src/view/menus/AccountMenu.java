package view.menus;

import controller.AccountController;
import model.account.Account;
import java.util.Scanner;

public class AccountMenu {
    private final Scanner scanner;
    private final AccountController accountController;

    public AccountMenu(Scanner scanner, AccountController accountController) {
        this.scanner = scanner;
        this.accountController = accountController;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== HESAP İŞLEMLERİ ===");
            System.out.println("1. Yeni Hesap Aç");
            System.out.println("2. Para Yatır");
            System.out.println("3. Para Çek");
            System.out.println("4. Transfer Yap");
            System.out.println("0. Ana Menüye Dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    openNewAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private void openNewAccount() {
        System.out.println("\n=== YENİ HESAP AÇMA ===");

        System.out.print("Hesap Numarası: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Hesap Sahibi Adı: ");
        String accountHolderName = scanner.nextLine();

        System.out.println("\nHesap Tipi:");
        System.out.println("1. Vadesiz Hesap");
        System.out.println("2. Vadeli Hesap");
        System.out.print("Seçiminiz: ");

        Account account;
        switch (scanner.nextInt()) {
            case 1:
                account = accountController.createCheckingAccount(accountNumber, accountHolderName);
                break;
            case 2:
                account = accountController.createSavingsAccount(accountNumber, accountHolderName);
                break;
            default:
                System.out.println("Geçersiz hesap tipi!");
                return;
        }
        scanner.nextLine();

        if (account != null) {
            System.out.println("Hesap başarıyla açıldı!");
            System.out.println("Hesap Numarası: " + account.getAccountNumber());
        } else {
            System.out.println("Hesap açma işlemi başarısız!");
        }
    }

    private void depositMoney() {
        System.out.println("\n=== PARA YATIRMA ===");

        System.out.print("Hesap Numarası: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Yatırılacak Miktar: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (accountController.deposit(accountNumber, amount)) {
            System.out.println("Para yatırma işlemi başarılı!");
        } else {
            System.out.println("Para yatırma işlemi başarısız!");
        }
    }

    private void withdrawMoney() {
        System.out.println("\n=== PARA ÇEKME ===");

        System.out.print("Hesap Numarası: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Çekilecek Miktar: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (accountController.withdraw(accountNumber, amount)) {
            System.out.println("Para çekme işlemi başarılı!");
        } else {
            System.out.println("Para çekme işlemi başarısız!");
        }
    }

    private void transferMoney() {
        System.out.println("\n=== PARA TRANSFERİ ===");

        System.out.print("Gönderen Hesap Numarası: ");
        String fromAccountNumber = scanner.nextLine();

        System.out.print("Alıcı Hesap Numarası: ");
        String toAccountNumber = scanner.nextLine();

        System.out.print("Transfer Miktarı: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (accountController.transfer(fromAccountNumber, toAccountNumber, amount)) {
            System.out.println("Transfer işlemi başarılı!");
        } else {
            System.out.println("Transfer işlemi başarısız!");
        }
    }
}