package exception;

public class InvalidDateException extends BankException {
    public InvalidDateException(String message) {
        super(ErrorCode.INVALID_DATE, message);
    }
}