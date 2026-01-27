package main;

import Util.FileUtil;
import model.*;
import service.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService = new CustomerService();
    private static EmployeeService employeeService = new EmployeeService();
    private static PlantService plantService = new PlantService();
    private static OrderService orderService = new OrderService(customerService, employeeService);
    private static Order currentOrder = null;

    public static void main(String[] args) {
        // Kies 1 van de 2 opties hieronder:

        // OPTIE 1: TEST ALLES AUTOMATISCH (voor demo)
        // runAlleTesten();

        // OPTIE 2: TOON MENU (voor eindgebruiker)
        System.out.println("=== DEN GROENEN HOND - PLANTENCENTRUM ===");
        System.out.println("Project: Bestellingen en Leveringen\n");
        toonHoofdmenu();
    }

    private static void runAlleTesten() {
        System.out.println("=== START ALLE TESTEN ===\n");

        // Test 1: Plant
        Plant plant1 = new Plant("Eik", 1.5, 0.3, 24.99, "Groen", PlantType.BOOM);
        System.out.println("Test 1 - Plant: " + plant1);

        // Test 2: Klant (particulier)
        Customer persoon1 = new Customer("Jan Janssen", "Straat 1, 8000 Brugge",
                "jan@mail.com", "0123456789");
        System.out.println("\nTest 2 - Klant (particulier): " + persoon1);

        // Test 3: Klant (bedrijf)
        Company klant1 = new Company("Boomkwekerij De Groene", "Bosweg 10, 9000 Gent",
                "info@boomkwekerij.be", "0987654321",
                "Piet De Baets", "BE0123456789");
        System.out.println("\nTest 3 - Klant (bedrijf): " + klant1);

        // Test 4: Personeelslid
        Employee werknemer = new Employee("Karel De Bezorger", "Leverstraat 5, 8200 Brugge",
                "karel@hond.be", "011223344");
        werknemer.addSkill("Bestelwagen rijbewijs");
        werknemer.addSkill("Vrachtwagen rijbewijs");
        werknemer.addSkill("Opleiding veiligheid");
        System.out.println("\nTest 4 - Personeelslid: " + werknemer);

        // Test 5: Bestelling
        Customer testKlant = new Customer("Test Klant", "Teststraat 1, 8000 Brugge",
                "test@test.be", "000000000");
        Order bestelling = new Order(testKlant);

        Plant plant2 = new Plant("Roos", 0.8, 0.2, 12.50, "Rood", PlantType.STRUIK);
        Plant plant3 = new Plant("Beuk", 2.0, 0.5, 45.00, "Geel", PlantType.BOOM);

        bestelling.addPlant(plant1);
        bestelling.addPlant(plant2);
        bestelling.addPlant(plant3);

        System.out.println("\nTest 5 - Bestelling: " + bestelling);
        System.out.println("Totaalprijs: €" + String.format("%.2f", bestelling.calculateTotalPrice()));
        System.out.println("Totaal volume: " + String.format("%.2f", bestelling.calculateTotalVolume()) + " m³");

        // Test 6: Levering berekeningen
        System.out.println("\nTest 6 - Levering Berekeningen:");
        System.out.println("Past in bestelwagen: " + bestelling.fitsInVan());
        System.out.println("Past in vrachtwagen: " + bestelling.fitsInTruck());
        System.out.println("Voertuig nodig: " + bestelling.determineVehicleType());

        // Test 7: Grote bestelling
        Order groteBestelling = new Order(testKlant);
        Plant grotePlant = new Plant("Grote Eik", 3.0, 1.0, 100.00, "Groen", PlantType.BOOM);
        groteBestelling.addPlant(grotePlant);

        System.out.println("\nTest 7 - Grote bestelling:");
        System.out.println("Max hoogte: " + groteBestelling.getMaxHeight() + " m");
        System.out.println("Max diameter: " + groteBestelling.getMaxDiameter() + " m");
        System.out.println("Voertuig nodig: " + groteBestelling.determineVehicleType());

        // Test 8: Bestelbon schrijven
        System.out.println("\nTest 8 - Bestelbon maken:");
        try {
            orderService.completeOrder(bestelling);
            String bestelbon = orderService.generateOrderReceipt(bestelling);
            System.out.println(bestelbon);

            String fileName = bestelling.generateFileName();
            FileUtil.saveToFile(fileName, bestelbon);
            System.out.println("Bestelbon opgeslagen als: " + fileName);
        } catch (Exception e) {
            System.out.println("Fout bij maken bestelbon: " + e.getMessage());
        }

        System.out.println("\n=== ALLE TESTEN VOLTOOID ===");
    }

    private static void toonHoofdmenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n=======================================");
            System.out.println("      DEN GROENEN HOND - Hoofdmenu");
            System.out.println("=======================================");
            System.out.println("1. Klant aanmaken");
            System.out.println("2. Personeelslid aanmaken");
            System.out.println("3. Nieuwe bestelling starten");
            System.out.println("4. Plant toevoegen aan bestelling");
            System.out.println("5. Bestelling afronden en bestelbon afdrukken");
            System.out.println("6. Alle klanten tonen");
            System.out.println("7. Alle personeelsleden tonen");
            System.out.println("8. Alle planten tonen");
            System.out.println("9. Alle bestellingen tonen");
            System.out.println("10. Testmodus uitvoeren");
            System.out.println("0. Afsluiten");
            System.out.println("=======================================");

            int choice = getIntInput("Keuze: ");

            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    createEmployee();
                    break;
                case 3:
                    startNewOrder();
                    break;
                case 4:
                    addPlantToOrder();
                    break;
                case 5:
                    completeAndPrintOrder();
                    break;
                case 6:
                    viewAllCustomers();
                    break;
                case 7:
                    viewAllEmployees();
                    break;
                case 8:
                    viewAllPlants();
                    break;
                case 9:
                    viewAllOrders();
                    break;
                case 10:
                    runAlleTesten();
                    break;
                case 0:
                    System.out.println("Applicatie wordt afgesloten...");
                    running = false;
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }

            if (running && choice != 0) {
                System.out.println("\nDruk op Enter om verder te gaan...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void createCustomer() {
        System.out.println("\n--- Nieuwe Klant Aanmaken ---");
        System.out.println("1. Particulier");
        System.out.println("2. Bedrijf");
        int typeChoice = getIntInput("Type klant: ");

        System.out.print("Naam: ");
        String name = scanner.nextLine();

        System.out.print("Adres: ");
        String address = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefoon: ");
        String phone = scanner.nextLine();

        try {
            if (typeChoice == 1) {
                Customer customer = new Customer(name, address, email, phone);
                customerService.addCustomer(customer);
                System.out.println("Particuliere klant aangemaakt met ID: " + customer.getCustomerId());
            } else if (typeChoice == 2) {
                System.out.print("Contactpersoon: ");
                String contactPerson = scanner.nextLine();

                System.out.print("BTW-nummer (BE0123456789): ");
                String vatNumber = scanner.nextLine();

                Company company = new Company(name, address, email, phone, contactPerson, vatNumber);
                customerService.addCustomer(company);
                System.out.println("Bedrijfsklant aangemaakt met ID: " + company.getCustomerId());
            } else {
                System.out.println("Ongeldige keuze.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    private static void createEmployee() {
        System.out.println("\n--- Nieuw Personeelslid Aanmaken ---");

        System.out.print("Naam: ");
        String name = scanner.nextLine();

        System.out.print("Adres: ");
        String address = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefoon: ");
        String phone = scanner.nextLine();

        try {
            Employee employee = new Employee(name, address, email, phone);

            // Vaardigheden toevoegen
            boolean addingSkills = true;
            while (addingSkills) {
                System.out.print("Vaardigheid toevoegen (of 'stop' om te stoppen): ");
                String skill = scanner.nextLine();

                if (skill.equalsIgnoreCase("stop")) {
                    addingSkills = false;
                } else {
                    employee.addSkill(skill);
                    System.out.println("Vaardigheid toegevoegd: " + skill);
                }
            }

            employeeService.addEmployee(employee);
            System.out.println("Personeelslid aangemaakt met ID: " + employee.getEmployeeId());
        } catch (IllegalArgumentException e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    private static void startNewOrder() {
        System.out.println("\n--- Nieuwe Bestelling ---");

        // Toon alle klanten
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Geen klanten gevonden. Maak eerst een klant aan.");
            return;
        }

        System.out.println("Beschikbare klanten:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getName());
        }

        int choice = getIntInput("Selecteer klant (nummer): ");
        if (choice < 1 || choice > customers.size()) {
            System.out.println("Ongeldige keuze.");
            return;
        }

        Customer selectedCustomer = customers.get(choice - 1);
        currentOrder = orderService.createOrder(selectedCustomer);

        System.out.println("Nieuwe bestelling aangemaakt met ID: " + currentOrder.getOrderId());
        System.out.println("Klant: " + selectedCustomer.getName());
    }

    private static void addPlantToOrder() {
        if (currentOrder == null) {
            System.out.println("Geen actieve bestelling. Start eerst een nieuwe bestelling.");
            return;
        }

        System.out.println("\n--- Plant Toevoegen ---");

        // Toon alle planten
        List<Plant> plants = plantService.getAllPlants();
        if (plants.isEmpty()) {
            System.out.println("Geen planten beschikbaar.");
            return;
        }

        System.out.println("Beschikbare planten:");
        for (int i = 0; i < plants.size(); i++) {
            Plant plant = plants.get(i);
            System.out.println((i + 1) + ". " + plant.getName() + " - €" +
                    String.format("%.2f", plant.getPrice()) + " - " +
                    plant.getType().getDisplayName());
        }

        int choice = getIntInput("Selecteer plant (nummer): ");
        if (choice < 1 || choice > plants.size()) {
            System.out.println("Ongeldige keuze.");
            return;
        }

        Plant selectedPlant = plants.get(choice - 1);
        currentOrder.addPlant(selectedPlant);

        System.out.println("Plant toegevoegd: " + selectedPlant.getName());
        System.out.println("Aantal planten in bestelling: " + currentOrder.getPlants().size());
        System.out.println("Huidige totaalprijs: €" +
                String.format("%.2f", currentOrder.calculateTotalPrice()));
    }

    private static void completeAndPrintOrder() {
        if (currentOrder == null || currentOrder.getPlants().isEmpty()) {
            System.out.println("Geen actieve bestelling of bestelling is leeg.");
            return;
        }

        System.out.println("\n--- Bestelling Afronden ---");

        try {
            // Complete de bestelling
            orderService.completeOrder(currentOrder);

            // Genereer bestelbon
            String receipt = orderService.generateOrderReceipt(currentOrder);

            // Toon bestelbon op scherm
            System.out.println("\n" + receipt);

            // Sla bestelbon op als tekstbestand
            String fileName = currentOrder.generateFileName();
            FileUtil.saveToFile(fileName, receipt);

            System.out.println("Bestelbon opgeslagen als: " + fileName);
            System.out.println("Bestelling succesvol afgerond!");

            // Reset currentOrder voor volgende bestelling
            currentOrder = null;

        } catch (Exception e) {
            System.out.println("Fout bij afronden bestelling: " + e.getMessage());
        }
    }

    private static void viewAllCustomers() {
        System.out.println("\n--- Alle Klanten ---");
        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("Geen klanten gevonden.");
        } else {
            for (Customer customer : customers) {
                System.out.println("---");
                System.out.println(customer.toString());
                System.out.println();
            }
        }
    }

    private static void viewAllEmployees() {
        System.out.println("\n--- Alle Personeelsleden ---");
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("Geen personeelsleden gevonden.");
        } else {
            for (Employee employee : employees) {
                System.out.println("---");
                System.out.println(employee.toString());
                System.out.println();
            }
        }
    }

    private static void viewAllPlants() {
        System.out.println("\n--- Alle Planten ---");
        List<Plant> plants = plantService.getAllPlants();

        if (plants.isEmpty()) {
            System.out.println("Geen planten gevonden.");
        } else {
            for (Plant plant : plants) {
                System.out.println("---");
                System.out.println(plant.toString());
                System.out.println("Volume: " + String.format("%.2f", plant.calculateVolume()) + " m³");
                System.out.println();
            }
        }
    }

    private static void viewAllOrders() {
        System.out.println("\n--- Alle Bestellingen ---");
        List<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("Geen bestellingen gevonden.");
        } else {
            for (Order order : orders) {
                System.out.println("---");
                System.out.println(order.toString());
                System.out.println("Aantal planten: " + order.getPlants().size());
                System.out.println("Totaalprijs: €" + String.format("%.2f", order.calculateTotalPrice()));

                if (order.getDeliveryDate() != null) {
                    java.time.format.DateTimeFormatter formatter =
                            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("Leverdatum: " + order.getDeliveryDate().format(formatter));
                }
                System.out.println();
            }
        }
    }

    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer. Voer een getal in.");
            }
        }
    }
}