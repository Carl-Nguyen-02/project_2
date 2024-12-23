package controller;

import javax.swing.*;

import view.LoginView;
import Application.Application;
import controller.cashier.CashierMainScreenController;
import controller.customer.CustomerMainScreenController;
import controller.manager.ManagerMainScreenController;
import DAO.SQLite;
import DAO.MongoDB;
import DAO.Redis;
import model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {
    private LoginView loginView;
    private SQLite sqLite;
    private MongoDB mongoDB;
    private Redis redis;


    public LoginController(LoginView loginView, SQLite sqLite, MongoDB mongoDB, Redis redis) {
        this.loginView = loginView;
        this.sqLite = sqLite;
        this.mongoDB = mongoDB;
        this.redis = redis;

        this.loginView.setVisible(true);
        this.loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
    }


    private void authenticateUser() {
        String username = loginView.getTxtUserName().getText();
        String password = String.valueOf(loginView.getTxtPassword().getPassword());

        // Authenticate using SQlite
        User user = sqLite.authenticate(username, password);

        if (user != null) {
            loginView.dispose(); // Close login screen

            int role = user.getRole();
            switch (role) {
                case 3: //Customer
                    String customerID = user.getCustomerID();
//                    Customer customer = mongoDB.ensureCustomerExists(customerID, user.getDisplayName(), true);
//                    user.setCustomer(customer);

                        new CustomerMainScreenController(user, customerID, sqLite, mongoDB, redis);
                    break;

                case 2: //Cashier
                    new CashierMainScreenController(user, sqLite, mongoDB, redis);
                    break;

                case 1: //Manager
                    new ManagerMainScreenController(user, sqLite, mongoDB, redis);
                    break;

                default:
                    JOptionPane.showMessageDialog(loginView, "Unknown user role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            loginView.displayErrorMessage("Invalid username or password");
        }
    }
}
