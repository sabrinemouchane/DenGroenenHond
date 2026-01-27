package model;

import java.util.Set;
import java.util.HashSet;

// Klasse voor Persoonlslid
class Employee extends Person {
    private String employeeId;
    private Set<String> skills; // Set voor unieke vaardigheden

    public Employee(String name, String address, String email,String phone){
      super(name, address, email, phone);
      this.employeeId = generateEmployeeId();
      this.skills = new HashSet<>();
    }

    private String generateEmployeeId(){
        return "EMP" + System.currentTimeMillis() % 10000;
    }

    //Vaardigheden beheren
    public void addSkill(String skill){
        skills.add(skill.toLowerCase());
    }

    public boolean hasSkill(String skill){
        return skills.contains(skill.toLowerCase());
    }

    public void removeSkill(String skill) {
        skills.remove(skill.toLowerCase());
    }

    public Set<String> getSkills(){
        return new HashSet<>(skills); // Retrun copy
    }

    // Specifieke vaardigheden controleren
    public boolean canDriveVan() {
        return hasSkill("rijbewijs bestelwagen");
    }

    public boolean canDriveTruck() {
        return hasSkill("rijbewijs vrachtwagen");
    }

    public String getEmployeeId() { return employeeId;}

    @Override
    public String toString() {
        return getName() + " (ID: " + employeeId + ") - Vaardigheden: " + skills;
    }
}
