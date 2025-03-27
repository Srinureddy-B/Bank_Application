package exception;

public class InvalidAddressException extends BankException {
    public InvalidAddressException(String message) {
        super(ErrorCode.INVALID_ADDRESS, message);
    }
}