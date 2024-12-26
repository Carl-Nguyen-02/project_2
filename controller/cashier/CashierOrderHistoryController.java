package controller.cashier;

import DAO.MongoDB;
import model.User;
import view.Cashier.CashierOrderHistoryView;

import javax.swing.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CashierOrderHistoryController {
    private CashierOrderHistoryView view;
    private MongoDB mongoDB;
    private User cashier;

    public CashierOrderHistoryController(User cashier, MongoDB mongoDB) {
        this.cashier = cashier;
        this.mongoDB = mongoDB;

        // Initialize the view
        view = new CashierOrderHistoryView();
        view.setVisible(true);

        // Add action listeners
        view.addApplyFiltersListener(e -> applyFilters());
        view.addViewOrderLinesListener(e -> viewOrderLines());
    }

    private void applyFilters() {
        try {
            // Get filter and sort options from the view
            String paymentMethod = view.getFilterPaymentMethod();
            String sortOption = view.getSortOption();

            // Build filter for payment method
            Bson paymentFilter = paymentMethod.equals("All")
                    ? Filters.eq("sourceID", String.valueOf(cashier.getUserID())) // Only show cashier's transactions
                    : Filters.and(
                    Filters.eq("sourceID", String.valueOf(cashier.getUserID())),
                    Filters.eq("paymentMethod", paymentMethod)
            );

            // Build sort option
            Bson sort = switch (sortOption) {
                case "Order Date (Ascending)" -> Sorts.ascending("orderDate");
                case "Order Date (Descending)" -> Sorts.descending("orderDate");
                case "Total Cost (Ascending)" -> Sorts.ascending("totalCost");
                case "Total Cost (Descending)" -> Sorts.descending("totalCost");
                default -> Sorts.ascending("orderDate");
            };

            // Fetch transactions from MongoDB
            List<Document> transactions = mongoDB.getCashierHistory(cashier)
                    .find(paymentFilter)
                    .sort(sort)
                    .into(new ArrayList<>());

            // Convert MongoDB documents to table rows
            List<Object[]> rows = transactions.stream()
                    .map(doc -> new Object[]{
                            doc.getString("orderID"),
                            doc.getString("orderDate"),
                            doc.getDouble("totalCost"),
                            doc.getString("paymentMethod")
                    })
                    .collect(Collectors.toList());

            // Populate the view table
            view.populateTable(rows);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Failed to fetch transactions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewOrderLines() {
        try {
            // Prompt user for Order ID
            String orderID = JOptionPane.showInputDialog(view, "Enter Order ID:");
            if (orderID == null || orderID.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter a valid Order ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Fetch the order from MongoDB
            Document order = mongoDB.getCashierHistory(cashier).find(Filters.eq("orderID", orderID)).first();

            if (order != null) {
                // Extract and display order lines
                List<Document> orderLines = (List<Document>) order.get("orderLines");
                StringBuilder details = new StringBuilder("Order Lines for Order ID: " + orderID + "\n\n");

                for (Document line : orderLines) {
                    details.append("Product ID: ").append(line.getInteger("productID"))
                            .append(", Product Name: ").append(line.getString("productName"))
                            .append(", Quantity: ").append(line.getDouble("quantity"))
                            .append(", Price: $").append(line.getDouble("price"))
                            .append(", Total Cost: $").append(line.getDouble("cost"))
                            .append("\n");
                }

                // Display the details in a message dialog
                JOptionPane.showMessageDialog(view, details.toString(), "Order Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Order not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Failed to fetch order details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

