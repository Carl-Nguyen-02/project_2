package view.Customer;

import javax.swing.*;
import java.awt.*;

import view.Layout.TransactionLayout;

public class CustomerOrderView extends JFrame{
    private TransactionLayout TransactionDetail;
    private JButton addProduct;
    private JButton removeProduct;
    private JButton finishAndPay;
    private JButton clearTransaction;
    private JComboBox<String> paymentMethod;
    private JLabel totalPriceLabel;

    public CustomerOrderView() {
        setTitle("Customer Order Screen");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        TransactionDetail = new TransactionLayout();
        addProduct = new JButton("Add/Edit Product");
        removeProduct = new JButton("Remove Product");
        finishAndPay = new JButton("Finish and Pay");
        clearTransaction = new JButton("Clear Transaction");

        String[] paymentOptions = {"Choose an option", "Cash", "E-wallet", "Debit Card"};
        paymentMethod = new JComboBox<>(paymentOptions);
        paymentMethod.setSelectedIndex(0);

        totalPriceLabel = new JLabel("Total Price: $0.00");

        JPanel buttonProductPanel = new JPanel();
        buttonProductPanel.setPreferredSize(new Dimension(600, 100));
        buttonProductPanel.add(addProduct);
        buttonProductPanel.add(removeProduct);
        buttonProductPanel.add(finishAndPay);
        buttonProductPanel.add(clearTransaction);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(buttonProductPanel);

        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dropdownPanel.add(new JLabel("Payment Method:"));
        dropdownPanel.add(paymentMethod);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(TransactionDetail);
        centerPanel.add(totalPriceLabel);
        centerPanel.add(buttonProductPanel); // Add button panel to center panel
        centerPanel.add(dropdownPanel);      // Add dropdown panel to center panel

        add(centerPanel);
    }

    public JButton getBtnProd() {
        return addProduct;
    }
    public JButton getBtnRemove() {
        return removeProduct;
    }
    public JButton getBtnClear() {
        return clearTransaction;
    }
    public JButton getBtnPay() {
        return finishAndPay;
    }
    public JComboBox<String> getPaymentMethod() {
        return paymentMethod;
    }
    public TransactionLayout getTransactionDetail() {
        return TransactionDetail;
    }
    public void setTotalPrice(double totalPrice) {
        totalPriceLabel.setText(String.format("Total Price: $%.2f", totalPrice));
    }
}
