package exception;

public class InvalidEmailException extends BankException {
    public InvalidEmailException(String message) {
        super(ErrorCode.INVALID_EMAIL, message);
    }
}