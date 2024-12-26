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

    public static TransactionInfo createFromProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid product or quantity.");
        }

        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock available.");
        }

        TransactionInfo orderLine = new TransactionInfo();
        orderLine.setProductID(product.getProductID());
        orderLine.setProductName(product.getName());
        orderLine.setPrice(product.getPrice());
        orderLine.setQuantity(quantity);
        orderLine.calculateCost(); // Automatically calculates cost

        return orderLine;
    }

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

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return totalCost;
    }

    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    private void calculateCost() {
        this.totalCost = this.price * this.quantity;
    }
}
