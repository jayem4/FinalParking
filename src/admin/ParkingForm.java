/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.dbConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ADMIN
 */
public class ParkingForm extends javax.swing.JFrame {

    /**
     * Creates new form ParkingForm
     */
    public ParkingForm() {
        initComponents();
        
        loadParkingTransactionsData();
    }

    public void displayParkingTransactions() {
    try {
        dbConnect dbc = new dbConnect();

        // Query to fetch relevant columns from 'parking_transactions' table
        ResultSet rs = dbc.getData("SELECT id, u_id, vehicle_type, payment, " +
                                   "CONCAT(date, ' ', time) AS datetime, status " +
                                   "FROM parking_transactions");

        // Assuming your JTable is named 'tblparking'
        tblparking.setModel(DbUtils.resultSetToTableModel(rs));

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

    DefaultTableModel model = new DefaultTableModel();

public void tableChanged(TableModelEvent e) {
    if (e.getType() == TableModelEvent.UPDATE) {
        int row = e.getFirstRow();
        int column = e.getColumn();

        if (row == -1 || column == -1) {
            return;
        }

        updateParkingDatabase(row, column); // ✅ Update parking transaction instead of reports
    }

    // Set column headers for parking_transactions table
    String[] columnNames = {
        "id", "u_id", "vehicle_type", "payment",
        "date", "time", "status"
    };
    model.setColumnIdentifiers(columnNames);
    model.setRowCount(0); // Clear table

    String sql = "SELECT id, u_id, vehicle_type, payment, date, time, status FROM parking_transactions";

    try (Connection connect = new dbConnect().getConnection();
         PreparedStatement pst = connect.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            Object[] row = {
                rs.getInt("id"),
                rs.getInt("u_id"),
                rs.getString("vehicle_type"),
                rs.getString("payment"),
                rs.getDate("date"),
                rs.getTime("time"),
                rs.getString("status")
            };
            model.addRow(row);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
private void updateParkingDatabase(int row, int column) {
    try {
        int transactionId = Integer.parseInt(model.getValueAt(row, 0).toString()); // Assuming ID is column 0
        String columnName = model.getColumnName(column);
        Object newValue = model.getValueAt(row, column);

        // Use parameterized query to update the specific column
        String sql = "UPDATE parking_transactions SET " + columnName + " = ? WHERE id = ?";

        try (Connection conn = new dbConnect().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setObject(1, newValue);
            pst.setInt(2, transactionId);
            pst.executeUpdate();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Update Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void loadParkingTransactionsData() {
    DefaultTableModel model = (DefaultTableModel) tblparking.getModel();  // Assuming 'tblblotter' is your JTable for parking transactions
    model.setRowCount(0); // Clear the table before reloading

    String sql = "SELECT id, u_id, vehicle_type, payment, date, time, status FROM parking_transactions";

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "");
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        // Iterate through ResultSet and add rows to the table
        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getInt("id"),
                rs.getInt("u_id"),
                rs.getString("vehicle_type"),
                rs.getString("payment"),
                rs.getDate("date"),
                rs.getTime("time"),
                rs.getString("status")
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading parking transactions data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblparking = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblparking.setBackground(new java.awt.Color(153, 255, 204));
        tblparking.setForeground(new java.awt.Color(51, 51, 51));
        tblparking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id ", "u_id", "vehicle_type", "payment", "date", "time", "status"
            }
        ));
        jScrollPane1.setViewportView(tblparking);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 720, 420));

        jButton1.setText("Select");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
         int selectedRow = tblparking.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a parking transaction to timeout.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Assuming 'id' is in column 0 of the table
    int parkingId = (int) tblparking.getValueAt(selectedRow, 0);

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "")) {

        String sql = "UPDATE parking_transactions SET status = ? WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "Timed Out");  // new status
        pst.setInt(2, parkingId);

        int affectedRows = pst.executeUpdate();

        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(this, "Parking transaction timed out successfully.");
            // Refresh the table data after update
            loadParkingTransactionsData();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update the parking transaction.", "Update Failed", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdminDashboard AD = new AdminDashboard();
        AD.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParkingForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblparking;
    // End of variables declaration//GEN-END:variables
}
