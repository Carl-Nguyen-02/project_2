package model;
public class Product {
    private int productID;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double sellPrice, int stock_quantity) {
        this.productID = id;
        this.name = name;
        this.quantity = stock_quantity;
        this.price = sellPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

