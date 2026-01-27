package model;

public enum OrderStatus {
    DRAFT("Concept"),
    PENDING("In behandeling"),
    CALCULATED("Berekend"),
    SCHEDULED("Gepland"),
    DELIVERED("Geleverd");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}