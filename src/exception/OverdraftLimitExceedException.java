package exception;

public class OverdraftLimitExceedException extends BankException {
    public OverdraftLimitExceedException(String message) {
        super(ErrorCode.OVERDRAFT_LIMIT_EXCEEDED, message);
    }
}
