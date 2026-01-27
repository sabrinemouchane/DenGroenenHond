package service;

import model.Customer;
import model.Company;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
        loadTestData();
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Klant mag niet null zijn");
        }
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public Optional<Customer> findCustomerById(int id) {
        return customers.stream()
                .filter(c -> {
                    if (c instanceof Company) {
                        return ((Company) c).getCustomerId() == id;
                    } else {
                        return ((Customer) c).getCustomerId() == id;
                    }
                })
                .findFirst();
    }

    public Optional<Customer> findCustomerByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    private void loadTestData() {
        // Testdata voor klanten
        customers.add(new Customer("Jan Janssens", "Dorpsstraat 1, 8000 Brugge",
                "jan.janssens@email.com", "050 12 34 56"));
        customers.add(new Customer("Marie Pieters", "Kerkplein 15, 9000 Gent",
                "marie.p@email.com", "09 87 65 43"));

        customers.add(new Company("Tuincentrum Groen", "Industrielaan 45, 8500 Kortrijk",
                "info@tuincentrumgroen.be", "056 78 90 12",
                "Peter De Smet", "BE0123456789"));
        customers.add(new Company("Hotel Belle Vue", "Strandlaan 2, 8400 Oostende",
                "receptie@bellevuehotel.be", "059 23 45 67",
                "Sarah Maes", "BE0987654321"));
    }
}