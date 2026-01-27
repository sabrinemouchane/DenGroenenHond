package model;

// Enum voor plantensoorten
enum PlantType{
    BOOM("Boom"),
    VASTE_PLANT("Vaste plant"),
    WATERPLANT("Waterplant"),
    STRUIK("Struik"),
    HEESTER("Heester");
    ;

    private final String displayName;

    PlantType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Methode om een type te vinden op basis van display name
    public static PlantType fromDisplayName(String name) throws IllegalAccessException{
        for ( PlantType type : values()){
            if (type.displayName.equalsIgnoreCase(name)){
                return type;
            }
        }
        throw new IllegalAccessException("Ongeldige planttype: " + name);
    }
}