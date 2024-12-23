//package Application;
//import java.sql.*;
//
////import DAO.DataAdapter;
//import DAO.MongoDB;
//import DAO.Redis;
//import DAO.SQLite;
//import view.LoginView;
//import view.MainScreen;
//import view.ProductView;
//import view.TransactionView;
//import controller.LoginController;
//import controller.TransactionController;
//import model.User;
//
//public class Application {
//    private static Application instance;   // Singleton pattern
//
//    public static Application getInstance() {
//        if (instance == null) {
//            instance = new Application();
//        }
//        return instance;
//    }
//
//    private Connection connection;
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public SQLite dataAdapter;
//    public Redis redisAdapter;
//    public MongoDB mongoDBAdapter;
//    public Redis getRedisAdapter() { return redisAdapter; }
//    public MongoDB getMongoDBAdapter() { return mongoDBAdapter; }
//    public void setRedisAdapter(Redis redisAdapter) { this.redisAdapter = redisAdapter; }
//    public void setMongoDBAdapter(MongoDB mongoDBAdapter) { this.mongoDBAdapter = mongoDBAdapter; }
//    public void setMongoDBAdapter(MongoDB mongoDBAdapter, Redis redisAdapter) {
//        this.mongoDBAdapter = mongoDBAdapter;
//        this.redisAdapter = redisAdapter;
//    }
//
//    private User currentUser = null;
//
//    public User getCurrentUser() { return currentUser; }
//
//    public void setCurrentUser(User user) {
//        this.currentUser = user;
//        transactionController.setUser(user);
//    }
//
//    private TransactionView checkoutScreen = new TransactionView();
//    private MainScreen mainScreen = new MainScreen();
//    private ProductView productScreen = new ProductView();
//
//    public MainScreen getMainScreen() {
//        return mainScreen;
//    }
//
//    public ProductView getProductView() {
//        return productScreen;
//    }
//
//    public TransactionView getCheckoutScreen() {
//        return checkoutScreen;
//    }
//
//    public LoginView loginScreen = new LoginView();
//
//    public LoginView getLoginView() {
//        return loginScreen;
//    }
//
//    public LoginController loginController;
//    public TransactionController transactionController;
//
//    public SQLite getDataAdapter() {
//        return dataAdapter;
//    }
//
//    private Application() {
//        // create SQLite database connection here!
//        try {
//            Class.forName("org.sqlite.JDBC");
//            String url = "jdbc:sqlite:store.db";
//            connection = DriverManager.getConnection(url);
//            dataAdapter = new SQLite(connection);
//        }
//        catch (ClassNotFoundException ex) {
//            System.out.println("SQLite is not installed. System exits with error!");
//            ex.printStackTrace();
//            System.exit(1);
//        }
//
//        catch (SQLException ex) {
//            System.out.println("SQLite database is not ready. System exits with error!" + ex.getMessage());
//
//            System.exit(2);
//        }
//
//        loginController = new LoginController(loginScreen, dataAdapter, mongoDBAdapter, redisAdapter);
////        transactionController = new TransactionController(checkoutScreen, dataAdapter, currentUser);
//    }
//
//    public static void main(String[] args) {
//        Application.getInstance().getLoginView().setVisible(true);
//    }
//}
package Application;

import controller.LoginController;
import DAO.SQLite;
import DAO.MongoDB;
import DAO.Redis;
import view.LoginView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private static Application instance;
    private SQLite sqLite;
    private MongoDB mongoDB;
    private Redis redis;
    private Connection connection;

    public static Application getInstance() {
        if (instance == null) {
            try {
                instance = new Application();
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return instance;
    }

    private Application() throws SQLException {
        try {
            // Initialize MySQL
            this.connection = DriverManager.getConnection(
                    "jdbc:sqlite:store.db"
            );
            this.sqLite = new SQLite(connection);

            // Initialize MongoDB
            this.mongoDB = new MongoDB();

            // Initialize Redis
            this.redis = new Redis(this.mongoDB);

            // Initialize LoginView and Controller
            LoginView loginView = new LoginView();
            new LoginController(loginView, sqLite, mongoDB, redis);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to SQLite: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Initialization failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> Application.getInstance());
    }
}


