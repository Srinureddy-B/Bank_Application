package exception;

public enum ErrorCode {
    INSUFFICIENT_BALANCE("Yetersiz Bakiye"),
    INVALID_AMOUNT("Geçersiz Tutar"),
    ACCOUNT_INACTIVE("Hesap Aktif Değil"),
    ACCOUNT_NOT_FOUND("Hesap Bulunamadı"),

    OVERDRAFT_LIMIT_EXCEEDED("Overdraft Limiti Aşıldı"),

    MINIMUM_BALANCE_VIOLATION("Minimum Bakiye İhlali"),
    NEGATIVE_RATE("Negatif Oran"),
    NEGATIVE_BALANCE("Negatif Bakiye"),

    TRANSACTION_FAILED("İşlem Başarısız"),
    INVALID_TRANSACTION("Geçersiz İşlem");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
