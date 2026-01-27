package model;

import java.text.DecimalFormat;

public class Plant {
    private static int plantCounter = 10000;
    private final String articleNumber;
    private String name;
    private double height; // in meters
    private double diameter; // in meters
    private double price;
    private String color;
    private PlantType type;

    public Plant(String name, double height, double diameter, double price, String color, PlantType type) {
        this.articleNumber = "PL" + (++plantCounter);
        setName(name);
        setHeight(height);
        setDiameter(diameter);
        setPrice(price);
        setColor(color);
        setType(type);
    }

    // Getters en setters
    public String getArticleNumber() { return articleNumber; }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        this.name = name.trim();
    }

    public double getHeight() { return height; }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Hoogte moet groter zijn dan 0");
        }
        this.height = height;
    }

    public double getDiameter() { return diameter; }

    public void setDiameter(double diameter) {
        if (diameter <= 0) {
            throw new IllegalArgumentException("Diameter moet groter zijn dan 0");
        }
        this.diameter = diameter;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Prijs mag niet negatief zijn");
        }
        this.price = Math.round(price * 100.0) / 100.0;
    }

    public String getColor() { return color; }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Kleur mag niet leeg zijn");
        }
        this.color = color.trim();
    }

    public PlantType getType() { return type; }

    public void setType(PlantType type) {
        if (type == null) {
            throw new IllegalArgumentException("Planttype mag niet null zijn");
        }
        this.type = type;
    }

    // Bereken volume (benadering als cilinder)
    public double calculateVolume() {
        double radius = diameter / 2;
        return Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "Artikelnummer: " + articleNumber + "\n" +
                "Naam: " + name + "\n" +
                "Type: " + type.getDisplayName() + "\n" +
                "Hoogte: " + height + " m\n" +
                "Diameter: " + diameter + " m\n" +
                "Kleur: " + color + "\n" +
                "Prijs: €" + df.format(price);
    }
}