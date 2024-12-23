package controller;

import DAO.SQLite;
import model.*;
import view.TransactionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TransactionController implements ActionListener {
    private TransactionView view;
    private SQLite dataAdapter; // to save and load product
    private Transaction order = null;
    private List<TransactionInfo> lines;
    private User loggedInUser;
    private double TotalPrice;

    public TransactionController(TransactionView view, SQLite dataAdapter, User employeeID) {
        this.dataAdapter = dataAdapter;
        this.view = view;
        this.loggedInUser = employeeID;
        view.getBtnAdd().addActionListener(this);
        view.getBtnPay().addActionListener(this);
        view.getBtnCus().addActionListener(this);

        order = new Transaction();
        lines = new ArrayList<>();

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Reset the view to its original state before closing
                clearTransaction(); // Clear all transaction details like order lines, total price, etc.

                // Dispose of the current view to free resources
                view.dispose();

                // Optionally, re-open a new TransactionView instance if needed
                // You can use the controller or parent class to re-initialize it.
//                SwingUtilities.invokeLater(() -> {
//                    TransactionView newView = new TransactionView(); // Create a new TransactionView instance
//                    new TransactionController(newView, dataAdapter, loggedInUser); // Attach new controller to the view
//                    newView.setVisible(true); // Display the new view
//                });
            }
        });

    }

    public void setUser(User user) {
        this.loggedInUser = user;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAdd())
            addProduct();
        if (e.getSource() == view.getBtnPay())
            makeOrder();
        if (e.getSource() == view.getBtnCus())
            addCustomer();
    }

    private void updateExistingProductQuantity(int productID, double quantity) {
        for (int i = order.getLines().size() - 1; i >= 0; i--) {
            TransactionInfo line = order.getLines().get(i);
            if (line.getProductID() == (productID)) {
                order.setTotalCost(order.getTotalCost() - line.getCost()); // Update order total cost

                // Find the row index corresponding to the productID
                int rowIndex = order.getLineIndex(line);
                if (rowIndex >= 0) { // If row exists in the table, safely remove it
                    this.view.removeRowByIndex(rowIndex);
                }

                // Remove the line from the order
                order.getLines().remove(i);
            }
        }
    }

    private void makeOrder() {
        JOptionPane.showMessageDialog(view, "This feature is being implemented");
//        if (order.getLines() == null || order.getLines().isEmpty()) {
//            JOptionPane.showMessageDialog(view, "Please select at least one order line");
//            return;
//        }
//
//        // Get customer info
//        String customerIdStr = JOptionPane.showInputDialog("Enter Customer ID:");
//        int customerId = Integer.parseInt(customerIdStr);
//
//
//        this.TotalPrice = order.getTotalCost();
//
//        // Create order
//        int orderId = dataAdapter.createOrder(loggedInUser.getUserID(), this.TotalPrice, customerId);
//
//        if (orderId != -1) {
//            // Add order lines
//            for (TransactionInfo line : lines) {
//                dataAdapter.createOrderLine(orderId, line.getProductID(), line.getQuantity(), line.getCost());
//                updateProductQuantity(line.getProductID(), line.getQuantity());
//            }
//
//            // Update customer info if needed
//            Customer customer = dataAdapter.loadCustomer(customerId);
//
//            JOptionPane.showMessageDialog(view, "Order created successfully!");
//
//            // Clear the transaction view
//            clearTransaction();
//        } else {
//            JOptionPane.showMessageDialog(view, "Error creating order.");
//        }
    }

//    private void updateProductQuantity(int productId, double quantity) {
//        Product product = dataAdapter.loadProduct(productId);
//        if (product != null) {
//            int currentStock = product.getQuantity();
//            int newStock = currentStock - (int) quantity;
//            if (newStock >= 0) {
//                product.setQuantity(newStock);
//                dataAdapter.saveProduct(product);
//            } else {
//                JOptionPane.showMessageDialog(view, "Insufficient stock for this product.");
//            }
//        } else {
//            JOptionPane.showMessageDialog(view, "Failed to find product in the database.");
//        }
//    }

    private void clearTransaction() {
        lines.clear();
        order.setTotalCost(0);
        view.getLabTotal().setText("Total: $0");
        view.clearTable();
    }



    private void addProduct(){
        JOptionPane.showMessageDialog(view, "This feature is being implemented");;
    }

    private void addCustomer(){
        JOptionPane.showMessageDialog(view, "This feature is being implemented");
    }
//    private void addProduct() {
//        String id = JOptionPane.showInputDialog("Enter ProductID: ");
//        Product product = dataAdapter.loadProduct(Integer.parseInt(id));
//        if (product == null) {
//            JOptionPane.showMessageDialog(null, "This product does not exist!");
//            return;
//        }
//
//        int quantity = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter quantity: "));
//
//        if (quantity < 0 || quantity > product.getQuantity()) {
//            JOptionPane.showMessageDialog(null, "This quantity is not valid!");
//            return;
//        }
//        if (quantity == 0){
//            if (!order.hasProduct(product.getProductID())) {
//                JOptionPane.showMessageDialog(null, "This quantity is not valid!");
//                return;
//            } else{
//                int removed_line_index = order.getLineFromID(product.getProductID());
//                TransactionInfo removed_line = order.getLines().get(removed_line_index);
//                this.view.removeRowByIndex(removed_line_index);
//                order.getLines().remove(removed_line_index);
//                order.setTotalCost(order.getTotalCost() - removed_line.getCost());
//                this.view.getLabTotal().setText("Total: $" + order.getTotalCost());
//            }
//        }
//        if (order.hasProduct(product.getProductID())) {
//            updateExistingProductQuantity(product.getProductID(), product.getQuantity());
//        }
//
//        if (quantity > 0) {
//            TransactionInfo line = new TransactionInfo();
//            line.setOrderID(this.order.getOrderID());
//            line.setProductID(product.getProductID());
//            line.setQuantity(quantity);
//            line.setCost(quantity * product.getPrice());
//            order.getLines().add(line);
//            order.setTotalCost(order.getTotalCost() + line.getCost());
//            Object[] row = new Object[5];
//            row[0] = line.getProductID();
//            row[1] = product.getName();
//            row[2] = product.getPrice();
//            row[3] = line.getQuantity();
//            row[4] = line.getCost();
//
//            this.view.addRow(row);
//            this.view.getLabTotal().setText("Total: $" + order.getTotalCost());
//            this.view.invalidate();
//        }
//    }
//
//    private void addCustomer() {
//        String cusID = JOptionPane.showInputDialog("Enter CustomerID: ");
//        Customer customer = dataAdapter.loadCustomer(Integer.parseInt(cusID));
//        if (customer == null){
//            JOptionPane.showMessageDialog(null, "This customer is not registered!");
//            return;
//        }
//    }

}