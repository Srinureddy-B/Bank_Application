package exception;

public class InvalidNameException extends BankException {
    public InvalidNameException(String message) {
        super(ErrorCode.INVALID_NAME, message);
    }
}