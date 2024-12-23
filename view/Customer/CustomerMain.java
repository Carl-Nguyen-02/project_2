package view.Customer;

import Application.Application;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

public class CustomerMain extends JFrame {
    private JButton btnOrder = new JButton("Order");
    private JButton btnHistory = new JButton("History");

    public CustomerMain() {
        setTitle("Customer Dashboard");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        components_init();
    }

    private void components_init() {
        JLabel title = new JLabel("Customer Dashboard");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnOrder);
        panelButtons.add(btnHistory);
        this.getContentPane().add(panelButtons);
    }

    public JButton getBtnHistory() {
        return btnHistory;
    }

    public JButton getBtnOrder() {
        return btnOrder;
    }

    public void addActionListener(ActionListener actionListener) {
        btnOrder.addActionListener(actionListener);
        btnHistory.addActionListener(actionListener);
    }
}
