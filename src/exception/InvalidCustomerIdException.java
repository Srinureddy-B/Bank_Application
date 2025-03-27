package exception;

public class InvalidCustomerIdException extends BankException {
    public InvalidCustomerIdException(String message) {
        super(ErrorCode.INVALID_CUSTOMER_ID, message);
    }
}