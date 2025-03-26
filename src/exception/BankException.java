package exception;

public class BankException extends RuntimeException{
    private final ErrorCode errorCode;

    public BankException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
