package model;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private int productID;
    private int orderID;
    private double quantity;
    private double cost;
    private int employeeID;
    private int buyerID;
    private String date;
    private List<TransactionInfo> lines;

    public Transaction(){
        lines = new ArrayList<>();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setTotalCost(double totalCost) {
        this.cost = totalCost;
    }

    public double getTotalCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void addLine(TransactionInfo line) {
        lines.add(line);
    }

    public void removeLine(TransactionInfo line) {
        lines.remove(line);
    }

    public List<TransactionInfo> getLines() {
        return lines;
    }

    public int getLineIndex(TransactionInfo line) {
        return lines.indexOf(line);
    }

    public boolean hasProduct(int productID) {
        for(TransactionInfo line : lines) {
            if(line.getProductID() == productID) {
                return true;
            }
        }
        return false;
    }

    public int getLineFromID(int productID){
        for(TransactionInfo line : lines) {
            if(line.getProductID() == productID) {
                return lines.indexOf(line);
            }
        }
        return -1;
    }


}
