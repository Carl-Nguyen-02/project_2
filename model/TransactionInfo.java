package model;

import java.util.ArrayList;
import java.util.List;

public class TransactionInfo {
    private int orderID;
    private String productName;
    private int productID;
    private int quantity;
    private double price;
    private double totalCost;

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(double cost) {
        this.totalCost = cost;
    }

    public double getCost() {
        return totalCost;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
}
