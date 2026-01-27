package model;

import java.util.regex.Pattern;

public abstract class Person {
    private String name;
    private String address;
    private String email;
    private String phone;

    public Person(String name, String address, String email, String phone) {
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhone(phone);
    }

    // Getters en setters
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        this.name = name.trim();
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Adres mag niet leeg zijn");
        }
        this.address = address.trim();
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Ongeldig email formaat");
        }
        this.email = email.trim();
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefoonnummer mag niet leeg zijn");
        }
        this.phone = phone.trim();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    @Override
    public String toString() {
        return "Naam: " + name + "\n" +
                "Adres: " + address + "\n" +
                "Email: " + email + "\n" +
                "Telefoon: " + phone;
    }
}