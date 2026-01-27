package service;

import model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
        loadTestData();
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Medewerker mag niet null zijn");
        }
        employees.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> findEmployeeById(int id) {
        return employees.stream()
                .filter(e -> e.getEmployeeId() == id)
                .findFirst();
    }

    public List<Employee> findEmployeesWithSkill(String skill) {
        return employees.stream()
                .filter(e -> e.hasSkill(skill))
                .toList();
    }

    private void loadTestData() {
        // Testdata voor medewerkers
        Employee emp1 = new Employee("Thomas Verstraete", "Eikenlaan 12, 8200 Brugge",
                "thomas.v@email.com", "050 11 22 33");
        emp1.addSkill("Bestelwagen rijbewijs");
        emp1.addSkill("Vrachtwagen rijbewijs");
        employees.add(emp1);

        Employee emp2 = new Employee("Lisa De Clercq", "Beukenstraat 34, 8300 Knokke",
                "lisa.dc@email.com", "050 44 55 66");
        emp2.addSkill("Bestelwagen rijbewijs");
        emp2.addSkill("Opleiding elektrische transpallet");
        employees.add(emp2);

        Employee emp3 = new Employee("Koen Demeyer", "Denneweg 56, 8000 Brugge",
                "koen.d@email.com", "050 77 88 99");
        emp3.addSkill("Vrachtwagen rijbewijs");
        emp3.addSkill("Opleiding veiligheid");
        employees.add(emp3);

        Employee emp4 = new Employee("An De Wilde", "Wilgenlaan 78, 8200 Sint-Andries",
                "an.dw@email.com", "050 99 00 11");
        emp4.addSkill("Bestelwagen rijbewijs");
        emp4.addSkill("Opleiding veiligheid");
        employees.add(emp4);
    }
}