package view.Cashier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CashierOrderHistoryView extends JFrame {
    private JTable transactionsTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> filterPaymentMethod;
    private JComboBox<String> sortOptions;
    private JButton viewOrderLinesButton;
    private JButton applyFiltersButton;

    private String[] columnNames = {"Order ID", "Order Date", "Total Cost", "Payment Method"};

    public CashierOrderHistoryView() {
        setTitle("Transaction History");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components
        tableModel = new DefaultTableModel(columnNames, 0);
        transactionsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(transactionsTable);

        // Filter panel
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());

        filterPanel.add(new JLabel("Filter by Payment Method:"));
        String[] paymentMethods = {"All", "Cash", "E-wallet", "Debit Card"};
        filterPaymentMethod = new JComboBox<>(paymentMethods);
        filterPanel.add(filterPaymentMethod);

        filterPanel.add(new JLabel("Sort by:"));
        String[] sortOptionsArray = {"Order Date (Ascending)", "Order Date (Descending)",
                "Total Cost (Ascending)", "Total Cost (Descending)"};
        sortOptions = new JComboBox<>(sortOptionsArray);
        filterPanel.add(sortOptions);

        applyFiltersButton = new JButton("Apply Filters");
        filterPanel.add(applyFiltersButton);

        // View Order Lines button
        viewOrderLinesButton = new JButton("Transaction Details");
        filterPanel.add(viewOrderLinesButton);

        add(scrollPane, BorderLayout.CENTER);
        add(filterPanel, BorderLayout.NORTH);

        // Event listeners
        addEventListeners();
    }

    public void populateTable(List<Object[]> data) {
        tableModel.setRowCount(0); // Clear existing rows
        for (Object[] row : data) {
            tableModel.addRow(row); // Add each row to the table
        }
    }


    public String getFilterPaymentMethod() {
        return (String) filterPaymentMethod.getSelectedItem();
    }

    public String getSortOption() {
        return (String) sortOptions.getSelectedItem();
    }

    public void addApplyFiltersListener(ActionListener listener) {
        applyFiltersButton.addActionListener(listener);
    }

    public void addViewOrderLinesListener(ActionListener listener) {
        viewOrderLinesButton.addActionListener(listener);
    }

    private void addEventListeners() {
        // Example for viewing order lines
        viewOrderLinesButton.addActionListener(e -> {
            String orderID = JOptionPane.showInputDialog(this, "Enter Order ID:");
            if (orderID != null && !orderID.trim().isEmpty()) {
                // Pass this order ID to the controller to fetch and display the order lines
                System.out.println("Order ID entered: " + orderID); // Replace with real logic
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid Order ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Example for applying filters and sorting
        applyFiltersButton.addActionListener(e -> {
            String selectedPaymentMethod = getFilterPaymentMethod();
            String selectedSortOption = getSortOption();
            // Pass these to the controller to fetch and display filtered and sorted data
            System.out.println("Filter: " + selectedPaymentMethod + ", Sort: " + selectedSortOption); // Replace with real logic
        });
    }
}

