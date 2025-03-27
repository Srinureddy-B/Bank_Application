package exception;

public enum ErrorCode {
    INSUFFICIENT_BALANCE("Yetersiz Bakiye"),
    INVALID_AMOUNT("Geçersiz Tutar"),
    INVALID_ACCOUNT_TYPE("Geçersiz Hesap Tipi"),
    ACCOUNT_INACTIVE("Hesap Aktif Değil"),
    ACCOUNT_NOT_FOUND("Hesap Bulunamadı"),
    OVERDRAFT_LIMIT_EXCEEDED("Overdraft Limiti Aşıldı"),
    MINIMUM_BALANCE_VIOLATION("Minimum Bakiye İhlali"),
    NEGATIVE_RATE("Negatif Oran"),
    NEGATIVE_BALANCE("Negatif Bakiye"),
    TRANSACTION_FAILED("İşlem Başarısız"),
    INVALID_TRANSACTION("Geçersiz İşlem"),
    ACCOUNT_ALREADY_EXISTS("Hesap Zaten Mevcut"),
    ACCOUNT_ALREADY_ACTIVE("Hesap Zaten Aktif"),
    ACCOUNT_ALREADY_INACTIVE("Hesap Zaten Pasif"),
    TARGET_ACCOUNT_INACTIVE("Hedef Hesap Aktif Değil");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
