package model.customer;

import java.time.LocalDate;
import java.util.*;
import model.account.Account;
import model.customer.enums.CustomerType;

public class Customer {
    private final UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private CustomerType customerType;

    private Set<Account> accounts;

    private Map<String, Account> accountsByNumber;

    public Customer(String firstName, String lastName, String email, CustomerType customerType) {
        this.customerId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerType = customerType;
        this.registrationDate = LocalDate.now();
        this.accounts = new HashSet<>();
        this.accountsByNumber = new HashMap<>();
    }

    public boolean addAccount(Account account) {
        if (accounts.contains(account)) {
            return false;
        }

        boolean added = accounts.add(account);
        if (added) {
            accountsByNumber.put(account.getAccountNumber(), account);
        }
        return added;
    }

    public boolean removeAccount(Account account) {
        boolean removed = accounts.remove(account);
        if (removed) {
            accountsByNumber.remove(account.getAccountNumber());
        }
        return removed;
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountsByNumber.get(accountNumber);
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public Map<String, Account> getAccountsMap() {
        return Collections.unmodifiableMap(accountsByNumber);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public double getTotalBalance() {
        return accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }


    public UUID getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return customerId.hashCode();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", name='" + getFullName() + '\'' +
                ", type=" + customerType +
                ", accounts=" + accounts.size() +
                '}';
    }
}