package view;

import controller.AccountController;
import controller.CustomerController;
import controller.TransactionController;
import view.menus.CustomerMenu;

import java.util.Scanner;

public class BankMenu {
    private final Scanner scanner;
    private final AccountController accountController;
    private final CustomerController customerController;
    private final TransactionController transactionController;
    private final CustomerMenu customerMenu;

    public BankMenu(AccountController accountController,
                    CustomerController customerController,
                    TransactionController transactionController) {
        this.scanner = new Scanner(System.in);
        this.accountController = accountController;
        this.customerController = customerController;
        this.transactionController = transactionController;
        this.customerMenu = new CustomerMenu(scanner, customerController);
    }

    public void start() {
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showCustomerMenu();
                    break;
                case 2:
                    showAccountMenu();
                    break;
                case 3:
                    showTransactionMenu();
                    break;
                case 4:
                    showReportMenu();
                    break;
                case 0:
                    System.out.println("Program sonlandırılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n=== BANKACILIK SİSTEMİ ===");
        System.out.println("1. Müşteri İşlemleri");
        System.out.println("2. Hesap İşlemleri");
        System.out.println("3. İşlem İşlemleri");
        System.out.println("4. Raporlar");
        System.out.println("0. Çıkış");
        System.out.print("Seçiminiz: ");
    }

    private void showCustomerMenu() {
        customerMenu.showMenu();
    }
}