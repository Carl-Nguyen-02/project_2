package model;

public class User {
private int userID;
private String username;
private String password;
private String fullName;
private int role;
private boolean isActive;
private String customerID;
private Customer customer;

public Customer getCustomer() {
    return customer;
}

public int getUserID() {
    return userID;
}

public void setUserID(int userID) {
    this.userID = userID;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getFullName() {
    return fullName;
}

public void setFullName(String fullName) {
    this.fullName = fullName;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public int getRole() {
    return role;
}

public void setRole(int role) {
    this.role = role;
}

public String getCustomerID() {
    return customerID; }

}

