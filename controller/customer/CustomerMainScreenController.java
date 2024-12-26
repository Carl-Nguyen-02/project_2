package controller.customer;

import DAO.MongoDB;
import DAO.SQLite;
import DAO.Redis;
import model.*;
import view.Customer.CustomerMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CustomerMainScreenController implements ActionListener {
    private CustomerMain view;
    private User customer;
    private MongoDB mongoDB;
    private SQLite sqlite;
    private Redis redis;

    public CustomerMainScreenController(User customer, String customerID,  SQLite sqlite, MongoDB mongoDB, Redis redis) {
        this.customer = customer;
        this.mongoDB = mongoDB;
        this.sqlite = sqlite;
        this.redis = redis;

        // Initialize the view
        this.view = new CustomerMain();
        this.view.setVisible(true);

        this.view.getBtnHistory().addActionListener(this);
        this.view.getBtnOrder().addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnOrder()) {
            new CustomerOrderController(customer, sqlite, mongoDB); // Open new order creation
        } else if (e.getSource() == view.getBtnHistory()) {
            new CustomerHistoryController(customer, sqlite, mongoDB); // Open product check
        }
    }
}
