package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class dbConnect {

    public Connection connect;

    public dbConnect() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "");
        } catch (SQLException ex) {
            System.err.println("Can't connect to database: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Database connection error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean insertData(String sql, Object... params) {
    try {
        PreparedStatement statement = connect.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        int rowsAffected = statement.executeUpdate();
        statement.close();
        return rowsAffected > 0; // Return true if insert was successful
    } catch (SQLException ex) {
        System.err.println("Database error: " + ex);
        JOptionPane.showMessageDialog(null, "Database error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}


    public ResultSet getData(String sql, Object... params) {
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex);
            JOptionPane.showMessageDialog(null, "Database error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Or throw an exception if appropriate
        }
    }

    public void updateData(String sql, Object... params) {
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Data Update Failed!");
            }
            statement.close();
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex);
            JOptionPane.showMessageDialog(null, "Database error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void closeConnection() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error closing connection: " + ex);
        }
    }

    public Connection getConnection() {
        return connect;
    }
}