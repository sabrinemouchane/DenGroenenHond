package service;

import model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private List<Order> orders;
    private CustomerService customerService;
    private EmployeeService employeeService;

    public OrderService(CustomerService customerService, EmployeeService employeeService) {
        this.orders = new ArrayList<>();
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    public Order createOrder(Customer customer) {
        Order order = new Order(customer);
        orders.add(order);
        return order;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> findOrderById(int id) {
        return orders.stream()
                .filter(o -> o.getOrderId() == id)
                .findFirst();
    }

    public List<Order> findOrdersByCustomer(Customer customer) {
        return orders.stream()
                .filter(o -> o.getCustomer().equals(customer))
                .toList();
    }

    public void completeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Bestelling mag niet null zijn");
        }

        // Bepaal voertuig type
        String vehicleType = order.determineVehicleType();
        order.setVehicleType(vehicleType);

        // Zoek beschikbare chauffeur
        Employee driver = findAvailableDriver(vehicleType);
        if (driver != null) {
            order.setAssignedDriver(driver);
        }

        // Bereken leverdatum (huidige datum + 0.5 dag = morgen)
        LocalDate deliveryDate = LocalDate.now().plusDays(1);
        order.setDeliveryDate(deliveryDate);

        order.setStatus(OrderStatus.CALCULATED);
    }

    private Employee findAvailableDriver(String vehicleType) {
        List<Employee> drivers;

        if (vehicleType.equals("Vrachtwagen")) {
            drivers = employeeService.findEmployeesWithSkill("Vrachtwagen rijbewijs");
        } else {
            drivers = employeeService.findEmployeesWithSkill("Bestelwagen rijbewijs");
        }

        return drivers.isEmpty() ? null : drivers.get(0);
    }

    public String generateOrderReceipt(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("=======================================\n");
        sb.append("        DEN GROENEN HOND\n");
        sb.append("        Bestelbon\n");
        sb.append("=======================================\n\n");

        sb.append("Bestelling ID: ").append(order.getOrderId()).append("\n");
        sb.append("Besteldatum: ").append(order.getOrderDate().format(formatter)).append("\n");
        sb.append("Leverdatum: ").append(order.getDeliveryDate().format(formatter)).append("\n\n");

        sb.append("Klantgegevens:\n");
        sb.append("--------------\n");
        sb.append(order.getCustomer().toString()).append("\n\n");

        sb.append("Bestelde planten:\n");
        sb.append("-----------------\n");

        for (Plant plant : order.getPlants()) {
            sb.append("- ").append(plant.getName()).append("\n");
            sb.append("  Artikel: ").append(plant.getArticleNumber()).append("\n");
            sb.append("  Type: ").append(plant.getType().getDisplayName()).append("\n");
            sb.append("  Prijs: €").append(String.format("%.2f", plant.getPrice())).append("\n\n");
        }

        sb.append("Samenvatting:\n");
        sb.append("-------------\n");
        sb.append("Aantal planten: ").append(order.getPlants().size()).append("\n");
        sb.append("Totaalprijs: €").append(String.format("%.2f", order.calculateTotalPrice())).append("\n");
        sb.append("Totaal volume: ").append(String.format("%.2f", order.calculateTotalVolume())).append(" m³\n");
        sb.append("Vervoer nodig: ").append(order.getVehicleType()).append("\n");

        if (order.getAssignedDriver() != null) {
            sb.append("Toegewezen chauffeur: ").append(order.getAssignedDriver().getName()).append("\n");
        }

        sb.append("\n=======================================\n");
        sb.append("Bedankt voor uw bestelling!\n");
        sb.append("=======================================\n");

        return sb.toString();
    }
}