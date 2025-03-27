package exception;

public class TransactionFailedException extends BankException {
    public TransactionFailedException(String message) {
        super(ErrorCode.TRANSACTION_FAILED, message);
    }
}