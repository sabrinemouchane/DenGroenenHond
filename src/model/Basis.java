package model;

// Basis Persoon klasse
abstract class Person {
    private String name;
    private String adress;
    private String email;
    private String phone;

    public Person(String name, String adress, String email, String phone)
    {
        this.name = name;
        this.adress = adress;
        this.email = email;
        this.phone = phone;
    }

    // Getters en setters
    public String getName() { return name;}
    public String getAdress() { return adress;}
    public String getEmail() { return email;}
    public String getPhone() { return phone;}

    public void setName(String name) { this.name = name;}
    public void setAdress(String adress) { this.adress = adress;}
    public void setEmail(String email) { this.email = email;}
    public void setPhone(String phone) { this.phone = phone;}
}