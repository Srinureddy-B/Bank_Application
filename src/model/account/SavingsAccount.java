package model.account;

import exception.InvalidAmountException;
import exception.MinimumBalanceViolationException;
import model.account.enums.AccountType;
import model.account.interfaces.IInterestCalculatable;

public class SavingsAccount extends Account implements IInterestCalculatable {
    private double interestRate;
    private double minimumBalance;

    public SavingsAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName, AccountType.SAVINGS);
        this.interestRate = 0.01;
        this.minimumBalance = 100.0;
    }

    @Override
    public void withdraw(double amount) {

        if (getBalance() - amount < minimumBalance) {
            throw new MinimumBalanceViolationException("Minimum bakiye koruması: En az " + minimumBalance + " tutarında bakiye kalmalıdır");
        }


        super.withdraw(amount);
    }

    @Override
    public double getMaxWithdrawLimit() {
        return getBalance() - minimumBalance;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double rate) {
        if (rate < 0) {
            throw new InvalidAmountException("Faiz oranı negatif olamaz");
        }
        this.interestRate = rate;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void applyInterest() {
        double interest = calculateInterest();
        if (interest > 0) {
            deposit(interest);
        }
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        if (minimumBalance < 0) {
            throw new InvalidAmountException("Minimum bakiye negatif olamaz");
        }
        this.minimumBalance = minimumBalance;
    }
}