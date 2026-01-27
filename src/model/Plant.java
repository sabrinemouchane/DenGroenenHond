package model;

import java.util.SplittableRandom;

// Klasse voor Plant
class Plant{
    private String artileNumber;
    private String name;
    private double height; // in cm
    private double diameter; // in cm
    private double price; // prijs met 2 decimalen
    private String color;
    private PlantType type;

    public Plant(String artileNumber, String name, double height, double diameter, double price,
                 String color, PlantType type){
        this.artileNumber = artileNumber;
        this.name = name;
        this.height = height / 100; // Converteer naa meter voor berekenigen
        this.diameter = diameter / 100; // Converteer naar meters
        this.price = Math.round(price * 100) / 100.0; // Afronden op 2 decimalen
        this.color = color;
        this.type = type;
    }

    // Volume berekenen (cylinder benadering)
    public double calculateVolume(){
        double radius = diameter / 2;
        return Math.PI * radius * radius * height;
    }

    // Getters
    public String getArtileNumber() { return  artileNumber;}
    public String getName() { return name;}
    public double getHeight() { return height;}
    public double getDiameter() { return diameter;}
    public double getPrice() { return price;}
    public String getColor() { return color;}
    public PlantType getType() {return type;}

    @Override
    public String toString() {
        return String.format("%s - %s (%.2fm, ⌀%.2fm) - €%.2f",
                artileNumber, name, height, diameter, price);
    }
}
