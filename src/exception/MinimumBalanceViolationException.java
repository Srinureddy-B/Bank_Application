package exception;

public class MinimumBalanceViolationException extends BankException {
    public MinimumBalanceViolationException(String message) {
        super(ErrorCode.MINIMUM_BALANCE_VIOLATION, message);
    }
}
