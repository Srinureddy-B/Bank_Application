package model.transaction.enums;

public enum TransactionType {
    DEPOSIT("Para Yatırma"),
    WITHDRAW("Para Çekme"),
    TRANSFER("Para Transferi");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}