package view.Manager;

import javax.swing.*;
import java.awt.*;

public class ManagerMain extends JFrame {
    private JButton stockManagementButton;
    private JButton orderHistoryButton;
    private JButton accountManagementButton;
    private JButton transactionHistoryButton;

    public ManagerMain() {
        setTitle("Manager Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        // Initialize buttons
        stockManagementButton = new JButton("Product Management");
        orderHistoryButton = new JButton("Order history");
        accountManagementButton = new JButton("Account Management");

        // Layout for buttons
        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(stockManagementButton);
        buttonPanel.add(orderHistoryButton);
        buttonPanel.add(accountManagementButton);

        // Add to frame
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);
    }

}
