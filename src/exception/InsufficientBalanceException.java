package exception;

public class InsufficientBalanceException extends BankException {
    public InsufficientBalanceException(String message) {
        super(ErrorCode.INSUFFICIENT_BALANCE, message);
    }
}
