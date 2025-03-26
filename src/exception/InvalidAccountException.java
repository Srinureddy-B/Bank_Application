package exception;

public class InvalidAccountException extends BankException {
    public InvalidAccountException(String message) {
        super(ErrorCode.INVALID_ACCOUNT_TYPE, message);
    }
}
