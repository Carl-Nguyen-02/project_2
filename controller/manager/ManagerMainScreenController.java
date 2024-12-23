package controller.manager;

import DAO.SQLite;
import DAO.Redis;
import DAO.MongoDB;
import model.User;
import view.Manager.ManagerMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMainScreenController {
    private ManagerMain view;
    private User manager;
    private MongoDB mongoDB;
    private SQLite sqlite;
    private Redis redis;

    public ManagerMainScreenController(User manager, SQLite sqlite, MongoDB mongoDB, Redis redis) {
            this.manager = manager;
            this.mongoDB = mongoDB;
            this.sqlite = sqlite;
            this.redis = redis;

            this.view = new ManagerMain();
            this.view.setVisible(true);
    }
}