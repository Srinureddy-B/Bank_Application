package model.customer.enums;

public enum CustomerType {
    INDIVIDUAL("Bireysel Müşteri"),
    CORPORATE("Kurumsal Müşteri");

    private final String description;

    CustomerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}