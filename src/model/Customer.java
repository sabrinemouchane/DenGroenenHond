package model;

// Klasse voor klant
class Customer extends Person {
    private String customerId;
    private boolean isCompany;
    private String vatNumber; // Alleen voor bedrijven
    private String contactPerson; // Alleen voor bedrijven

    public Customer(String name, String address, String email, String phone,
                    boolean isCompany, String vatNumber, String contactPerson)
{       super(name, address, email, phone);
        this.customerId = generateCustomerId();
        this.isCompany = isCompany;

        if(isCompany) {
            this.vatNumber = validateVat(vatNumber) ? vatNumber : null;
            this.contactPerson = contactPerson;
        } else {
            this.vatNumber = null;
            this.contactPerson = null;
        }
    }

    private String generateCustomerId() {
        return "CUST" + System.currentTimeMillis() % 10000;
    }

    private boolean validateVat(String vat){
        // Eenvoudige VAT validatie - in relaiteit zou dit complexer zijn
        return vat != null && vat.matches("BE[0-9]{10}");
    }

    //Getters
    public String getCustomerId() {return customerId;}
    public boolean isCompany() {return isCompany;}
    public String getVatNumber() {return vatNumber;}
    public String getContactPerson() {return contactPerson;}

    @Override
    public String toString() {
        if (isCompany) {
            return getName() + " (Bedrijf) - BTW: " + vatNumber + " - Contact: " + contactPerson;
        }
        return getName() + " (Particulier)";
    }
}
