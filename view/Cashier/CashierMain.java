package view.Cashier;

import Application.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierMain extends JFrame {
    private JButton btnTransact = new JButton("New Transaction");
    private JButton btnHistory = new JButton("Transaction History");

    public CashierMain() {
        setTitle("Cashier Dashboard");

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        btnHistory.setPreferredSize(new Dimension(120, 50));
        btnTransact.setPreferredSize(new Dimension(120, 50));

        JLabel title = new JLabel("Cashier Dashboard");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(btnTransact);
        panelButton.add(btnHistory);

        this.getContentPane().add(panelButton);
    }

    public JButton getTransactionButton() {
        return btnTransact;
    }

    public JButton getProductButton() {
        return btnHistory;
    }
}
