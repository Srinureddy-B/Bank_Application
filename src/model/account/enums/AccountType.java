package model.account.enums;

public enum AccountType {
    SAVINGS("Vadeli Hesab"),
    CHECKING("Vadesiz Hesap");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
