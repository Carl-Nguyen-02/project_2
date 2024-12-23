package view.Layout;

import model.Transaction;
import model.TransactionInfo;
import model.Product;

import javax.swing.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TransactionLayout extends JPanel {
    private JTable orderLineTable;
    private DefaultTableModel tableModel;
    private String[] columnNames = {"Product ID", "Product Name", "Price", "Quantity", "Total Cost"};

    public TransactionLayout() {
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        // Initialize table model and table
        tableModel = new DefaultTableModel(columnNames, 0);
        orderLineTable = new JTable(tableModel);
        orderLineTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(orderLineTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to populate the table with order line data
    public void setTableData(List<TransactionInfo> lines) {
        tableModel.setRowCount(0); // Clear existing rows

        for (TransactionInfo line : lines) {
            tableModel.addRow(new Object[]{
                    line.getProductID(),
                    line.getProductName(),
                    line.getPrice(),
                    line.getQuantity(),
                    line.getCost()
            });
        }
    }

    // Get the selected product ID
    public int getSelectedProductID() {
        int selectedRow = orderLineTable.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public JTable getOrderLineTable() {
        return orderLineTable;
    }
}
