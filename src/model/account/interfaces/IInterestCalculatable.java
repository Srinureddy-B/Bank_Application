package model.account.interfaces;

public interface IInterestCalculatable {
    double getInterestRate();
    void setInterestRate(double rate);
    double calculateInterest();
    void applyInterest();
}
