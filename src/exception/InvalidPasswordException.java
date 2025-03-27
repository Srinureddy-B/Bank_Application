package exception;

public class InvalidPasswordException extends BankException {
    public InvalidPasswordException(String message) {
        super(ErrorCode.INVALID_PASSWORD, message);
    }
}