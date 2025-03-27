package exception;

public class EmptyInputException extends BankException {
    public EmptyInputException(String message) {
        super(ErrorCode.EMPTY_INPUT, message);
    }
}