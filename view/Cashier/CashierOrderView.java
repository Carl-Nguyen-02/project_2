package view.Cashier;

import Application.Application;

import javax.swing.*;
import java.awt.*;

public class CashierOrderView extends JFrame {


    private JButton addProduct;
    private JButton finishAndPay;
    private JButton addCustomer;

    public CashierOrderView() {
        setTitle("Cashier - New Order");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addProduct = new JButton("Add Product");
        finishAndPay = new JButton("Finish and Pay");
        addCustomer = new JButton("Add Customer");

        JPanel buttonProductPanel = new JPanel();
        buttonProductPanel.setPreferredSize(new Dimension(600, 100));
        buttonProductPanel.add(addProduct);
        buttonProductPanel.add(finishAndPay);
        buttonProductPanel.add(addCustomer);
    }

    public JButton getBtnProd() {
        return addProduct;
    }
    public JButton getBtnPay() {
        return finishAndPay;
    }
    public JButton getCusBtn() {
        return addCustomer;
    }
}
