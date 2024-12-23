package controller.cashier;

import DAO.MongoDB;
import DAO.SQLite;
import model.*;
import view.Cashier.CashierOrderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class CashierOrderController implements ActionListener{
    private CashierOrderView view;
    private User cashier;
    private SQLite sqlite;
    private MongoDB mongoDB;
    private List<TransactionInfo> orderLines; // should be changed to Dict for quantity <>
    private String customerId;
    private Map<Integer, Integer> productQuantityMap; // Map for product ID and quantity

    public CashierOrderController(User cashier, SQLite sqlite, MongoDB mongoDB) {
        this.cashier = cashier;
        this.sqlite = sqlite;
        this.mongoDB = mongoDB;
        this.orderLines = new ArrayList<>();
        this.productQuantityMap = new HashMap<>();


        view = new CashierOrderView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnPay()) {
            handlePay();
        } else if (e.getSource() == view.getBtnProd()) {
            handleProduct();
        } else if (e.getSource() == view.getCusBtn()) {
            handleCustomer();
        }
    }

    private void handlePay(){
        return ;
    }

    private void handleProduct(){
        return ;
    }

    private void handleCustomer(){
        return ;
    }
}

