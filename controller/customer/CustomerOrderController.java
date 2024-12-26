package controller.customer;

import DAO.MongoDB;
import DAO.SQLite;
import model.Product;
import model.Transaction;
import model.TransactionInfo;
import model.User;
import view.Customer.CustomerOrderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CustomerOrderController implements ActionListener {
    private CustomerOrderView view;
    private User customer;
    private SQLite sqlite;
    private MongoDB mongoDB;
    private List<TransactionInfo> itemsPurchased;
    private Map<Integer, Integer> productQuantityMap; // Map for product ID and quantity

    public CustomerOrderController(User customer, SQLite sqlite, MongoDB mongoDB) {
        this.customer = customer;
        this.sqlite = sqlite;
        this.mongoDB = mongoDB;
        this.itemsPurchased = new ArrayList<>();
        this.productQuantityMap = new HashMap<>();

        view = new CustomerOrderView();
        view.setVisible(true);

        this.view.getBtnProd().addActionListener(this);
        this.view.getBtnRemove().addActionListener(this);
        this.view.getBtnPay().addActionListener(this);
        this.view.getBtnClear().addActionListener(this);
        this.view.getPaymentMethod().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnPay()) {
            finishOrder();
        } else if (e.getSource() == view.getBtnProd()) {
            add_edit();
        } else if (e.getSource() == view.getBtnRemove()) {
            remove_from_order();
        } else if (e.getSource() == view.getBtnClear()) {
            clear_transaction();
        }
    }

    private String generateOrderIDForCustomer() {
        return "CUS-" + System.currentTimeMillis();
    }

    private String generateOrderDate() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new java.util.Date());
    }

    private void clear_transaction() {
        if (productQuantityMap.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Order is already empty.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to clear the entire order?",
                "Confirm Clear Order",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            productQuantityMap.clear();
            updateOrderLinesTable();
            JOptionPane.showMessageDialog(view, "Order cleared successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void finishOrder(){
        if (productQuantityMap.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Order is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selectedPaymentMethod = (String) view.getPaymentMethod().getSelectedItem();
        if (selectedPaymentMethod.equals("Choose an option")) {
            JOptionPane.showMessageDialog(view, "Please select a valid payment method.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Transaction order = new Transaction();
        order.setSourceType("Order"); //Transaction for Cashiers, Order for Customers
        order.setOrderID(generateOrderIDForCustomer()); // Generate a unique order ID
        order.setSourceID(String.valueOf(customer.getUserID()));
        order.setPaymentMethod(selectedPaymentMethod);
        order.setOrderDate(generateOrderDate()); // Generate the current timestamp
        order.setLines(new ArrayList<>()); // Ensure lines is initialized

        // Populate order lines and reduce stock
        for (Map.Entry<Integer, Integer> entry : productQuantityMap.entrySet()) {
            Product product = sqlite.getProductByID(entry.getKey());
            if (product != null) {
                TransactionInfo itemPurchased = TransactionInfo.createFromProduct(product, entry.getValue());
                order.getLines().add(itemPurchased); // Add to the order's lines
                sqlite.reduceProductStock(product.getProductID(), entry.getValue());
            }
        }

        // Calculate total cost
        double totalCost = order.getLines().stream()
                .mapToDouble(TransactionInfo::getCost)
                .sum();
        order.setTotalCost(totalCost);

        // Save the order in MongoDB
        mongoDB.saveTransaction(order);

        // Show success message and clear the order
        JOptionPane.showMessageDialog(view, "Order placed successfully! Order ID: " + order.getOrderID(), "Success", JOptionPane.INFORMATION_MESSAGE);
        productQuantityMap.clear();
        updateOrderLinesTable();
        view.getPaymentMethod().setSelectedIndex(0);
    }

    private void updateTotalCostLabel() {
        double totalCost = 0;

        // Recalculate the total cost from the productQuantityMap
        for (Map.Entry<Integer, Integer> entry : productQuantityMap.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            // Fetch the product details and calculate the total cost
            Product product = sqlite.getProductByID(productId);
            if (product != null) {
                totalCost += product.getPrice() * quantity;
            }
        }

        // Update the total price JLabel
        view.setTotalPrice(totalCost);
    }

    private void add_edit() {
        String productID = JOptionPane.showInputDialog(view, "Enter Product ID:");
        if (!sqlite.productExists(Integer.parseInt(productID))) {
            JOptionPane.showMessageDialog(view, "Product does not exist");
        }
        if (productID.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Can't leave this field empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int productIDInt = Integer.parseInt(productID);
            if (productIDInt <= 0) {
                JOptionPane.showMessageDialog(view, "Please enter a valid value (Product ID cannot be negative).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!sqlite.productExists(productIDInt)) {
                JOptionPane.showMessageDialog(view, "This product doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Proceed to quantity input
            String quantity = JOptionPane.showInputDialog(view, "Enter Quantity:");
            if (quantity == null || quantity.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Can't leave this field empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt < 0) {
                    JOptionPane.showMessageDialog(view, "Quantity can't be negative", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (sqlite.verifyStock(productIDInt, quantityInt)){
//                    productQuantityMap.merge(productIDInt, quantityInt, Integer::sum);
                    productQuantityMap.put(productIDInt, quantityInt);
                    updateOrderLinesTable();
                    updateTotalCostLabel();
                } else{
                    JOptionPane.showMessageDialog(view, "Insufficient stock for the given quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter a valid numeric value for the quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remove_from_order(){
        String productID = JOptionPane.showInputDialog(view, "Enter Product ID:");
        if (productID == null || productID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Product ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int productIDInt;
        try {
            productIDInt = Integer.parseInt(productID);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Please enter a valid numeric Product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (productIDInt < 0) {
            JOptionPane.showMessageDialog(view, "Product ID cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Remove the product from the map
        if (productQuantityMap.containsKey(productIDInt)) {
            productQuantityMap.remove(productIDInt);
            updateOrderLinesTable();
            updateTotalCostLabel();
        } else {
            JOptionPane.showMessageDialog(view, "Product not found in the order.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOrderLinesTable() {
        // Clear the current orderLines list and rebuild it from the productQuantityMap
        itemsPurchased.clear();
        for (Map.Entry<Integer, Integer> entry : productQuantityMap.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = sqlite.getProductByID(productId);
            if (product != null) {
                TransactionInfo orderLine = TransactionInfo.createFromProduct(product, quantity);
                itemsPurchased.add(orderLine);
            }
        }

        // Update the table with the new orderLines list
        view.getTransactionDetail().setTableData(itemsPurchased);
    }
}


