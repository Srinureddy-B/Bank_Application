package view;

import controller.AccountController;
import controller.CustomerController;
import controller.TransactionController;
import view.menus.AccountMenu;
import view.menus.CustomerMenu;
import view.menus.ReportMenu;
import view.menus.TransactionMenu;

import java.util.Scanner;

public class BankMenu {
    private final Scanner scanner;
    private final AccountController accountController;
    private final CustomerController customerController;
    private final TransactionController transactionController;
    private final CustomerMenu customerMenu;
    private final AccountMenu accountMenu;
    private final TransactionMenu transactionMenu;
    private final ReportMenu reportMenu;

    public BankMenu(AccountController accountController,
                    CustomerController customerController,
                    TransactionController transactionController) {
        this.scanner = new Scanner(System.in);
        this.accountController = accountController;
        this.customerController = customerController;
        this.transactionController = transactionController;
        this.customerMenu = new CustomerMenu(scanner, customerController);
        this.accountMenu = new AccountMenu(scanner, accountController);
        this.transactionMenu = new TransactionMenu(scanner, transactionController);
        this.reportMenu = new ReportMenu(scanner, accountController, transactionController);
    }

    public void start() {
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerMenu.showMenu();
                    break;
                case 2:
                    accountMenu.showMenu();
                    break;
                case 3:
                    transactionMenu.showMenu();
                    break;
                case 4:
                    reportMenu.showMenu();
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
}