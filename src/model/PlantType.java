package model;

public enum PlantType {
    BOOM("Boom"),
    VASTE_PLANT("Vaste plant"),
    WATERPLANT("Waterplant"),
    STRUIK("Struik"),
    HEESTER("Heester");

    private final String displayName;

    PlantType(String displayName) {
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