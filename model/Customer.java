package model;

public class Customer {
    private String customerID;
    private String fullName;
    private String emailAddress;
    private int phoneNumber;

    public Customer(String fullName, String id) {
        this.fullName = fullName;
        this.customerID = id;
//        this.cart = new Cart(); // Initialize an empty cart
    }
    // Getters and Setters
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}