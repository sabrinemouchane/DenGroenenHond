package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Klasse voor Bestelling
class Order{
    private String orderId;
    private Customer customer;
    private LocalDate orderDate;
    private List<Plant> plants;
    private boolean isCompleted;

    public Order(Customer customer){
        this.orderId = generateOrderId();
        this.customer = customer;
        this.orderDate = LocalDate.now();
        this.plants = new ArrayList<>();
        this.isCompleted = false;
    }

    private String generateOrderId(){
        return "ORD" + System.currentTimeMillis() % 10000;
    }

    // Plant toevoegen aan bestelling
    public void addPlant(Plant plant){
        if (!isCompleted){
            plants.add(plant);
        }
    }

    // Totale prijs berekenen
    public double calculateTotalPrice(){
        return plants.stream()
                .mapToDouble(Plant::getPrice)
                .sum();
    }

    // Totale volume berekenen
    public double calculateTotalVolume(){
        return plants.stream()
                .mapToDouble(Plant::calculateVolume)
                .sum();
    }

    // Grootste dimensies vinden
    public double getMaxLength(){
        return plants.stream()
                .mapToDouble(Plant::getHeight)
                .max()
                .orElse(0);
    }

    public double getMaxDiameter(){
        return plants.stream()
                .mapToDouble(Plant::getDiameter)
                .max()
                .orElse(0);
    }

    // Getters
    public String getOrderId() { return orderId;}
    public Customer getCustomer() { return customer;}
    public LocalDate getOrderDate() { return orderDate;}
    public List<Plant> getPlants() { return new ArrayList<>(plants);}
    public boolean isCompleted() { return isCompleted;}

    public void setCompleted(boolean completed) { isCompleted = completed;}

    @Override
    public String toString(){
        return String.format("Bestelling %s - %s %d planten - Totaal: €%.2f",
                orderId, customer.getName(), plants.size(), calculateTotalPrice());
    }
}