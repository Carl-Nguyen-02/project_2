package DAO;

import java.sql.*;

import model.Product;
import model.Transaction;
import model.User;
import model.Customer;
import model.TransactionInfo;

public class SQLite {
    private Connection connection;

    public SQLite(Connection connection) {
        this.connection = connection;
    }

    public User authenticate(String username, String password) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE Users.Username = ? AND Users.Password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("ID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setFullName(resultSet.getString("Name"));
                user.setRole(resultSet.getInt("role_id"));
                resultSet.close();
                statement.close();

                return user;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }


    }



