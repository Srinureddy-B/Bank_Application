package exception;

public class InvalidAccountNumberException extends BankException {
  public InvalidAccountNumberException(String message) {
    super(ErrorCode.INVALID_ACCOUNT_NUMBER, message);
  }
}