package model;

public class Company extends Customer {
    private String contactPerson;
    private String vatNumber;

    public Company(String companyName, String address, String email, String phone,
                   String contactPerson, String vatNumber) {
        super(companyName, address, email, phone);
        setContactPerson(contactPerson);
        setVatNumber(vatNumber);
    }

    public String getContactPerson() { return contactPerson; }

    public void setContactPerson(String contactPerson) {
        if (contactPerson == null || contactPerson.trim().isEmpty()) {
            throw new IllegalArgumentException("Contactpersoon mag niet leeg zijn");
        }
        this.contactPerson = contactPerson.trim();
    }

    public String getVatNumber() { return vatNumber; }

    public void setVatNumber(String vatNumber) {
        if (vatNumber == null || !vatNumber.matches("BE[0-9]{10}")) {
            throw new IllegalArgumentException("BTW-nummer moet formaat BE0123456789 hebben");
        }
        this.vatNumber = vatNumber.trim();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Contactpersoon: " + contactPerson + "\n" +
                "BTW-nummer: " + vatNumber + "\n" +
                "Type: Bedrijf";
    }
}