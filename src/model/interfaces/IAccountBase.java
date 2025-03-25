package model.interfaces;

import java.time.LocalDateTime;

public interface IAccountBase {
    String getAccountNumber();
    double getBalance();
    String getAccountHolderName();
    LocalDateTime getCreation();
    boolean isActive();
}
