package exception;

public class InvalidAmountException extends BankException {
    public InvalidAmountException(String message) {
        super(ErrorCode.INVALID_AMOUNT, message);
    }
}
