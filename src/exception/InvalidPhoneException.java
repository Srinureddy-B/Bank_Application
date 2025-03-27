package exception;

public class InvalidPhoneException extends BankException {
    public InvalidPhoneException(String message) {
        super(ErrorCode.INVALID_PHONE, message);
    }
}