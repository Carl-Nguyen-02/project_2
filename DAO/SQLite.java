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

    public boolean productExists(int productID) {
        String query = "SELECT COUNT(*) FROM Products WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If the count is greater than 0, the product exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if the product doesn't exist or if an exception occurs
    }

    public boolean verifyStock(int productID, int quantity){
        String query = "SELECT Stock FROM Products WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int currentStock = rs.getInt("stock");
                return currentStock >= quantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductByID(int productId) {
        String query = "SELECT * FROM Products WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("ProductID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reduceProductStock(int productId, int quantity) {
        String query = "UPDATE Products SET stock = stock - ? WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }



