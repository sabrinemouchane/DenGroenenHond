package model;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
    private static int employeeCounter = 100;
    private final int employeeId;
    private List<String> skills;

    public Employee(String name, String address, String email, String phone) {
        super(name, address, email, phone);
        this.employeeId = ++employeeCounter;
        this.skills = new ArrayList<>();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public List<String> getSkills() {
        return new ArrayList<>(skills);
    }

    public void addSkill(String skill) {
        if (skill == null || skill.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill mag niet leeg zijn");
        }
        if (!skills.contains(skill.trim())) {
            skills.add(skill.trim());
        }
    }

    public boolean hasSkill(String skill) {
        return skills.contains(skill);
    }

    public void removeSkill(String skill) {
        skills.remove(skill);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Medewerker ID: ").append(employeeId).append("\n")
                .append(super.toString()).append("\n")
                .append("Vaardigheden: ");

        if (skills.isEmpty()) {
            sb.append("Geen");
        } else {
            sb.append(String.join(", ", skills));
        }

        return sb.toString();
    }
}