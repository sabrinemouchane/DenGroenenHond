package model;

public class Customer extends Person{
    private static int customerCounter = 1000;
    private final int customerId;

    public Customer(String name, String address, String email, String phone) {
        super(name, address, email, phone);
        this.customerId = ++customerCounter;
    }

    public int getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Klant ID: " + customerId + "\n" + super.toString();
    }
}