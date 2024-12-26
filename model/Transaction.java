package model;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String orderID;
    private String customerID;  // Optional: Nullable for orders without a customer
    private String customerName;  // Optional: Useful if customer info exists
    private String orderDate;
    private List<TransactionInfo> lines;
    private double totalCost;
    private String paymentMethod;

    private String sourceType;  // "customer" or "cashier"
    private String sourceID;    // The ID of the customer or cashier who created the order

    // Getters and Setters
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<TransactionInfo> getLines() {
        return lines;
    }

    public void setLines(List<TransactionInfo> lines) {
        this.lines = lines;
        calculateTotalCost();
    }

    public double getTotalCost() {
        return totalCost;
    }

    private void calculateTotalCost() {
        this.totalCost = 0;
        for (TransactionInfo line : lines) {
            this.totalCost += line.getCost();
        }
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost=totalCost;

    }


    public void addOrderLine(TransactionInfo fromProduct) {
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
