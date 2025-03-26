package exception;

public class AccountInactiveException extends BankException {
    public AccountInactiveException(String message) {
        super(ErrorCode.ACCOUNT_INACTIVE, message);
    }
}
