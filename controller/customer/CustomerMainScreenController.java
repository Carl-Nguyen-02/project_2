package controller.customer;

import DAO.Redis;
import DAO.SQLite;
import DAO.MongoDB;
import model.User;
import view.Customer.CustomerMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMainScreenController {
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
//        addListeners();
    }

    private void addListeners(){
        // Add action listeners to buttons
//        view.getBtnHistory().addActionListener(this);
//        view.getBtnOrder().addActionListener(this);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == view.getBtnOrder()) {
//            new CustomerOrderController(customer.getCustomer(), sqlite, mongoDB); // Open new order creation
//        } else if (e.getSource() == view.getBtnHistory()) {
//            new CustomerHistoryController(customer.getCustomer(), sqlite, mongoDB); // Open product check
//        }
//    }
}
