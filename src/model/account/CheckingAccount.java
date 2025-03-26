package model.account;

import model.account.enums.AccountType;

public class CheckingAccount extends Account {
    private double overdraftLimit;
    private double overdraftFee;

    public CheckingAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName, AccountType.CHECKING);
        this.overdraftLimit = 0.0;
        this.overdraftFee = 0.0;
    }

    @Override
    public void withdraw(double amount) {

        if (getBalance() >= amount) {
            super.withdraw(amount);
        } else if (getBalance() + overdraftLimit >= amount) {

            double remainingAmount = amount - getBalance();
            super.withdraw(getBalance());


            deposit(remainingAmount);
            withdraw(overdraftFee); // Overdraft ücreti
        } else {
            throw new IllegalStateException("Overdraft limiti aşıldı");
        }
    }

    @Override
    public double getMaxWithdrawLimit() {
        return getBalance() + overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }
}