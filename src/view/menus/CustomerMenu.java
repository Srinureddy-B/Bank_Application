package view.menus;

import controller.CustomerController;
import model.account.Account;
import model.customer.Customer;
import model.customer.enums.CustomerType;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CustomerMenu {
    private final Scanner scanner;
    private final CustomerController customerController;

    public CustomerMenu(Scanner scanner, CustomerController customerController) {
        this.scanner = scanner;
        this.customerController = customerController;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== MÜŞTERİ İŞLEMLERİ ===");
            System.out.println("1. Yeni Müşteri Kaydı");
            System.out.println("2. Müşteri Bilgilerini Görüntüle");
            System.out.println("3. Müşteri Bilgilerini Güncelle");
            System.out.println("0. Ana Menüye Dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerNewCustomer();
                    break;
                case 2:
                    displayCustomerInfo();
                    break;
                case 3:
                    updateCustomerInfo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private void registerNewCustomer() {
        System.out.println("\n=== YENİ MÜŞTERİ KAYDI ===");

        System.out.print("Ad: ");
        String firstName = scanner.nextLine();

        System.out.print("Soyad: ");
        String lastName = scanner.nextLine();

        System.out.print("E-posta: ");
        String email = scanner.nextLine();

        System.out.println("\nMüşteri Tipi:");
        System.out.println("1. Bireysel");
        System.out.println("2. Kurumsal");
        System.out.print("Seçiminiz: ");

        CustomerType customerType = scanner.nextInt() == 1 ?
                CustomerType.INDIVIDUAL : CustomerType.CORPORATE;
        scanner.nextLine();

        Customer customer = new Customer(firstName, lastName, email,customerType);

        if (customerController.registerCustomer(customer)) {
            System.out.println("Müşteri başarıyla kaydedildi!");
        } else {
            System.out.println("Müşteri kaydı başarısız!");
        }
    }

    private void displayCustomerInfo() {
        System.out.println("\n=== MÜŞTERİ BİLGİLERİ ===");
        System.out.print("Müşteri ID: ");
        UUID customerId = UUID.fromString(scanner.nextLine());

        List<Account> accounts = customerController.getCustomerAccounts(customerId);
        if (accounts != null) {
            System.out.println("\nMüşteri Hesapları:");
            accounts.forEach(account -> System.out.println(account));
        } else {
            System.out.println("Müşteri bulunamadı!");
        }
    }

    private void updateCustomerInfo() {
        System.out.println("\n=== MÜŞTERİ BİLGİLERİNİ GÜNCELLE ===");
        System.out.print("Müşteri ID: ");
        UUID customerId = UUID.fromString(scanner.nextLine());

        System.out.print("Yeni Ad: ");
        String firstName = scanner.nextLine();

        System.out.print("Yeni Soyad: ");
        String lastName = scanner.nextLine();

        System.out.print("Yeni E-posta: ");
        String email = scanner.nextLine();

        Customer updatedCustomer = new Customer(firstName, lastName, email, CustomerType.INDIVIDUAL);

        if (customerController.updateCustomerInfo(customerId, updatedCustomer)) {
            System.out.println("Müşteri bilgileri başarıyla güncellendi!");
        } else {
            System.out.println("Müşteri bilgileri güncellenemedi!");
        }
    }
}