package service;

import exception.ErrorCode;
import exception.InvalidAccountException;
import model.account.Account;
import model.account.enums.AccountType;
import model.customer.Customer;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerService {
    // Veri Yapıları:
    // HashMap kullanıyoruz çünkü:
    // - O(1) erişim süresi sağlar
    // - Key-value yapısı müşteri-hesp ilişkisini temsil eder
    // - Thread-safe değil, bu yüzden final olarak tanımladık
    private final Map<UUID, Customer> customers;
    private final Map<UUID, List<Account>> customerAccounts;

    // Constructor:
    // - Boş HashMap'ler oluşturarak başlangıç veri yapılarını hazırlıyoruz
    // - Bu sayede null pointer exception riskini ortadan kaldırıyoruz
    public CustomerService() {
        this.customers = new HashMap<>();
        this.customerAccounts = new HashMap<>();
    }

    // Müşteri Kaydı:
    // - Yeni müşteri kaydı yapıyoruz
    // - İki Map'e de ekleme yapıyoruz çünkü:
    //   * customers: Müşteri bilgilerini tutar
    //   * customerAccounts: Müşteri hesaplarını tutar
    public void registerCustomer(Customer customer) {
        if (customer == null) {
            throw new InvalidAccountException(ErrorCode.INVALID_TRANSACTION.getMessage());
        }
        if (customers.containsKey(customer.getCustomerId())) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
        }
        customers.put(customer.getCustomerId(), customer);
        customerAccounts.put(customer.getCustomerId(), new ArrayList<>());
    }

    // Optional Kullanımı:
    // Optional kullanıyoruz çünkü:
    // - Null kontrolü daha güvenli
    // - Müşteri bulunamazsa boş Optional döner
    // - NullPointerException riskini azaltır
    public Customer getCustomer(UUID customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        return customer;
    }

    // Güvenli Liste Dönüşü:
    // getOrDefault kullanıyoruz çünkü:
    // - Müşteri bulunamazsa null yerine boş liste döner
    // - NullPointerException riskini ortadan kaldırır
    // - Kod daha güvenli ve okunabilir olur
    public List<Account> getCustomerAccounts(UUID customerId) {
        if (!customers.containsKey(customerId)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        return customerAccounts.getOrDefault(customerId, new ArrayList<>());
    }

    // Güvenli Liste İşlemleri:
    // computeIfAbsent kullanıyoruz çünkü:
    // - Null kontrolü yapmamıza gerek kalmıyor
    // - Yeni müşteri için otomatik liste oluşturuyor
    // - Daha güvenli kod
    public void addAccountToCustomer(UUID customerId, Account account) {
        if (!customers.containsKey(customerId)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        if (account == null) {
            throw new InvalidAccountException(ErrorCode.INVALID_TRANSACTION.getMessage());
        }
        customerAccounts.computeIfAbsent(customerId, k -> new ArrayList<>())
                .add(account);
    }

    // Stream API Kullanımı:
    // Stream API kullanıyoruz çünkü:
    // - Daha okunabilir kod
    // - Daha az döngü kullanımı
    // - Daha performanslı işlemler
    // mapToDouble kullanıyoruz çünkü:
    // - Account nesnelerinden double değerleri alıyoruz
    // - sum() ile toplama yapıyoruz
    public double getCustomerTotalBalance(UUID customerId) {
        if (!customers.containsKey(customerId)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        return getCustomerAccounts(customerId).stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    // Stream API ile Filtreleme:
    // filter kullanıyoruz çünkü:
    // - Belirli koşullara uyan hesapları filtreliyoruz
    // - collect ile sonuçları listeye çeviriyoruz
    // - Daha temiz ve anlaşılır kod
    public List<Account> getCustomerAccountsByType(UUID customerId, AccountType type) {
        if (!customers.containsKey(customerId)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        return getCustomerAccounts(customerId).stream()
                .filter(account -> account.getAccountType() == type)
                .collect(Collectors.toList());
    }

    // Güvenli Güncelleme:
    // containsKey kullanıyoruz çünkü:
    // - Müşterinin var olup olmadığını kontrol ediyoruz
    // - Gereksiz güncelleme işlemlerini önlüyoruz
    // - Daha güvenli kod
    public void updateCustomerInfo(UUID customerId, Customer updatedCustomer) {
        if (!customers.containsKey(customerId)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        if (updatedCustomer == null) {
            throw new InvalidAccountException(ErrorCode.INVALID_TRANSACTION.getMessage());
        }
        customers.put(customerId, updatedCustomer);
    }

    // Güvenli Silme İşlemi:
    // removeIf kullanıyoruz çünkü:
    // - Predicate ile silme koşulunu belirliyoruz
    // - Null kontrolü yapıyoruz
    // - Daha güvenli silme işlemi
    public void closeCustomerAccount(UUID customerId, String accountNumber) {
        if (!customers.containsKey(customerId)) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        List<Account> accounts = customerAccounts.get(customerId);
        if (accounts == null || accounts.isEmpty()) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        boolean removed = accounts.removeIf(account -> account.getAccountNumber().equals(accountNumber));
        if (!removed) {
            throw new InvalidAccountException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
        }
    }
}