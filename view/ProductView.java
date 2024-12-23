package view;

import javax.swing.*;
import java.awt.*;


public class ProductView extends JFrame{
    private JButton btnAdd = new JButton("Add new product");
    private JButton btnEdit = new JButton("Edit existing product");

    public ProductView() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        btnAdd.setPreferredSize(new Dimension(120, 50));
        btnEdit.setPreferredSize(new Dimension(120, 50));


        JLabel title = new JLabel("Store Management System");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(btnEdit);
        panelButton.add(btnAdd);
    }
}
