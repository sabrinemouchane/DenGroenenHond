package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 100000;
    private final int orderId;
    private Customer customer;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private OrderStatus status;
    private List<Plant> plants;
    private Employee assignedDriver;
    private String vehicleType;

    public Order(Customer customer) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.orderDate = LocalDate.now();
        this.status = OrderStatus.DRAFT;
        this.plants = new ArrayList<>();
    }

    // Getters en setters
    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public LocalDate getOrderDate() { return orderDate; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public OrderStatus getStatus() { return status; }
    public List<Plant> getPlants() { return new ArrayList<>(plants); }
    public Employee getAssignedDriver() { return assignedDriver; }
    public String getVehicleType() { return vehicleType; }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Klant mag niet null zijn");
        }
        this.customer = customer;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        if (deliveryDate != null && deliveryDate.isBefore(orderDate)) {
            throw new IllegalArgumentException("Leverdatum kan niet voor besteldatum zijn");
        }
        this.deliveryDate = deliveryDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setAssignedDriver(Employee driver) {
        this.assignedDriver = driver;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void addPlant(Plant plant) {
        if (plant == null) {
            throw new IllegalArgumentException("Plant mag niet null zijn");
        }
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public double calculateTotalPrice() {
        return plants.stream()
                .mapToDouble(Plant::getPrice)
                .sum();
    }

    public double calculateTotalVolume() {
        return plants.stream()
                .mapToDouble(Plant::calculateVolume)
                .sum();
    }

    public double getMaxLength() {
        // Roept gewoon getMaxHeight() aan aangezien lengte = hoogte voor planten
        return getMaxHeight();
    }

    public double getMaxHeight() {
        return plants.stream()
                .mapToDouble(Plant::getHeight)
                .max()
                .orElse(0);
    }

    public double getMaxDiameter() {
        return plants.stream()
                .mapToDouble(Plant::getDiameter)
                .max()
                .orElse(0);
    }

    public boolean fitsInVan() {
        double totalVolume = calculateTotalVolume();
        double maxHeight = getMaxHeight();
        double maxDiameter = getMaxDiameter();

        // Controleer bestelwagen maten
        boolean fitsVolume = totalVolume <= 3.7; // max 3.7 m³
        boolean fitsHeight = maxHeight <= 2.1; // max 2.1 m hoogte
        boolean fitsWidth = maxDiameter <= 1.23; // max 1.23 m breedte

        return fitsVolume && fitsHeight && fitsWidth;
    }

    public boolean fitsInTruck() {
        double maxHeight = getMaxHeight();
        double maxDiameter = getMaxDiameter();

        // Controleer vrachtwagen maten
        boolean fitsHeight = maxHeight <= 2.4; // max 2.4 m hoogte
        boolean fitsWidth = maxDiameter <= 2.1; // max 2.1 m breedte

        return fitsHeight && fitsWidth;
    }

    public String determineVehicleType() {
        if (fitsInVan()) {
            return "Bestelwagen";
        } else if (fitsInTruck()) {
            return "Vrachtwagen";
        } else {
            return "Speciaal transport nodig";
        }
    }

    public String generateFileName() {
        String customerName = customer.getName().toLowerCase()
                .replace(" ", ".")
                .replaceAll("[^a-zA-Z0-9.-]", "");
        String date = orderDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return customerName + "-" + date + ".txt";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("Bestelling ID: ").append(orderId).append("\n")
                .append("Klant: ").append(customer.getName()).append("\n")
                .append("Besteldatum: ").append(orderDate.format(formatter)).append("\n")
                .append("Status: ").append(status.getDisplayName()).append("\n")
                .append("Aantal planten: ").append(plants.size()).append("\n");

        if (deliveryDate != null) {
            sb.append("Geplande leverdatum: ").append(deliveryDate.format(formatter)).append("\n");
        }

        if (vehicleType != null) {
            sb.append("Vervoer: ").append(vehicleType).append("\n");
        }

        if (assignedDriver != null) {
            sb.append("Chauffeur: ").append(assignedDriver.getName()).append("\n");
        }

        return sb.toString();
    }
}