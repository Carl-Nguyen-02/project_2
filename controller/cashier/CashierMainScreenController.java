package controller.cashier;

import DAO.MongoDB;
import DAO.SQLite;
import DAO.Redis;
import model.User;
import view.Cashier.CashierMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierMainScreenController implements ActionListener {
    private CashierMain view;
    private User cashier;
    private MongoDB mongoDB;
    private SQLite sqlite;
    private Redis redis;

    public CashierMainScreenController(User cashier,  SQLite sqlite, MongoDB mongoDB, Redis redis) {
        this.cashier = cashier;
        this.mongoDB = mongoDB;
        this.sqlite = sqlite;
        this.redis = redis;

        // Initialize the view
        this.view = new CashierMain();
        this.view.setVisible(true);

        // Add action listeners to buttons
        this.view.getTransactionButton().addActionListener(this);
        this.view.getProductButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getTransactionButton()) {
            new CashierOrderController(cashier, sqlite, mongoDB); // Open new order creation
        } else if (e.getSource() == view.getProductButton()) {

        }
    }
}
