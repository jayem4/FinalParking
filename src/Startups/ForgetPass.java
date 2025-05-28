/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Startups;

import Startups.Login;
import config.Session;
import java.awt.Color;
import config.dbConnect;
import config.passwordHasher;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author DANIEL FAILADONA
 */
public class ForgetPass extends javax.swing.JFrame {

    private String correctAnswer;

    private Color H;
    Color h = new Color(51,51,255);
    private Color D;
    Color d = new Color(240,240,240);
    
    public ForgetPass() {
        initComponents();
    }
    
    
  
     private void fetchSecurityQuestion() {
    String username = un.getText();  
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your username.");
        return;
    }

    // Create a database connection
    dbConnect db = new dbConnect();  // Instantiate dbConnector
    Connection con = db.getConnection(); // Get connection

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
        return;
    }

    try {
        PreparedStatement stmt = con.prepareStatement(
            "SELECT security_question, security_answer FROM tbl_accounts WHERE u_username = ?"
        );
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            sq.removeAllItems();
            sq.addItem(rs.getString("security_question"));
            sq.setEnabled(true);
            correctAnswer = rs.getString("security_answer");
            Submit.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
    } finally {
        try {
            if (con != null) {
                con.close(); // Close the connection after use
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    
    
    public void logEvent(int userId, String username, String action) 
    {
        dbConnect dbc = new dbConnect();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;
        Timestamp time = new Timestamp(new Date().getTime());

        try 
        {
            String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) "
                    + "VALUES ('" + userId + "', '" + username + "', '" + time + "', '" + action + "')";
            pstmt = con.prepareStatement(sql);

            /*            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.setString(4, userType);*/
            pstmt.executeUpdate();
            System.out.println("Login log recorded successfully.");
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
        } finally //This is a database closer
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                if (con != null) 
                {
                    con.close();
                }
            } catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
    }
    
    
    
    
    
    
    private void resetPassword() {
    String enteredAnswer = ans.getText();
    String newPassword = new String(Newpass.getPassword());

    // Declare variables here to fix your error
    int userId = -1;
    String uname2 = "";

    if (correctAnswer == null) {
        JOptionPane.showMessageDialog(this, "Please search for your username first.");
        return;
    }

    try {
        if (!passwordHasher.hashPassword(enteredAnswer).equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Incorrect security answer.");
            return;
        }
    } catch (NoSuchAlgorithmException ex) {
        JOptionPane.showMessageDialog(this, "Error verifying answer: " + ex.getMessage());
        return;
    }

    try {
        // Hash the new password before storing it
        String hashedPassword = passwordHasher.hashPassword(newPassword);

        // Instantiate the database connection
        dbConnect db = new dbConnect();
        Connection con = db.getConnection();
        dbConnect connector = new dbConnect(); // For logging query
        Session sess = Session.getInstance();

        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
            return;
        }

        try {
            // Update password in the database
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE tbl_accounts SET u_password = ? WHERE u_username = ?"
            );
            stmt.setString(1, hashedPassword);
            stmt.setString(2, un.getText());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password successfully reset!");

                // Try to log the password reset
                try {
                    String query2 = "SELECT * FROM tbl_accounts WHERE u_username = ?";
                    PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);
                    pstmt.setString(1, un.getText());

                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                        userId = resultSet.getInt("u_id");
                        uname2 = resultSet.getString("u_username");

                        // Log the event
                   logEvent(userId, uname2, "User Reset Their Password");

                    }
                } catch (SQLException ex) {
                    System.out.println("Log Error (SQL): " + ex.getMessage());
                }

                dispose(); // Close the window
            } else {
                JOptionPane.showMessageDialog(this, "Error: Username not found or password update failed.");
            }

        } finally {
            con.close();
        }

    } catch (NoSuchAlgorithmException ex) {
        JOptionPane.showMessageDialog(this, "Error hashing password: " + ex.getMessage());
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred while updating the password.");
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

        Main = new javax.swing.JPanel();
        submit = new javax.swing.JPanel();
        Submit = new javax.swing.JLabel();
        sq = new javax.swing.JComboBox<>();
        ans = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        acc_id1 = new javax.swing.JLabel();
        acc_id2 = new javax.swing.JLabel();
        Newpass = new javax.swing.JPasswordField();
        Cpass = new javax.swing.JPasswordField();
        acc_id3 = new javax.swing.JLabel();
        check = new javax.swing.JCheckBox();
        check1 = new javax.swing.JCheckBox();
        logout = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Main.setBackground(new java.awt.Color(204, 153, 0));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitMouseExited(evt);
            }
        });
        submit.setLayout(null);

        Submit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Submit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Submit.setText("Confirm");
        submit.add(Submit);
        Submit.setBounds(0, 10, 90, 10);

        Main.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 360, 90, 30));

        sq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's the name of your first pet?", "What's the lastname of your Mother?", "What's your favorite food?", "What's your favorite Color?", "What's your birth month?" }));
        Main.add(sq, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 500, 30));
        Main.add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 500, 30));
        Main.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 410, 30));

        acc_id1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_id1.setForeground(new java.awt.Color(255, 255, 255));
        acc_id1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id1.setText("Username:");
        Main.add(acc_id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, 30));

        acc_id2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_id2.setForeground(new java.awt.Color(255, 255, 255));
        acc_id2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id2.setText("Enter New Password:");
        Main.add(acc_id2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 200, 30));

        Newpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewpassActionPerformed(evt);
            }
        });
        Main.add(Newpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 490, 30));

        Cpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CpassActionPerformed(evt);
            }
        });
        Main.add(Cpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 490, 30));

        acc_id3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_id3.setForeground(new java.awt.Color(255, 255, 255));
        acc_id3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id3.setText("Confirm Password:");
        Main.add(acc_id3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 180, 30));

        check.setBackground(new java.awt.Color(51, 51, 51));
        check.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        check.setForeground(new java.awt.Color(255, 255, 255));
        check.setText("Show");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });
        Main.add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, -1, 30));

        check1.setBackground(new java.awt.Color(51, 51, 51));
        check1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        check1.setForeground(new java.awt.Color(255, 255, 255));
        check1.setText("Show");
        check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check1ActionPerformed(evt);
            }
        });
        Main.add(check1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 270, -1, 30));

        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Back");
        logout.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, -1));

        Main.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_gihapon.jpg"))); // NOI18N
        Main.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 580));

        jButton1.setText("Search");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        Main.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        Login ed = new Login();
        ed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(h);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(d);
    }//GEN-LAST:event_logoutMouseExited

    private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked
        resetPassword();
    }//GEN-LAST:event_submitMouseClicked

    private void submitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseEntered
        submit.setBackground(h);
    }//GEN-LAST:event_submitMouseEntered

    private void submitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseExited
        submit.setBackground(d);
    }//GEN-LAST:event_submitMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    
    }//GEN-LAST:event_formWindowActivated

    private void NewpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewpassActionPerformed

    private void CpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CpassActionPerformed

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        boolean isSelected = check.isSelected();

        if (isSelected) {
            Newpass.setEchoChar((char)0);
        } else {
            Newpass.setEchoChar('*');
        }
    }//GEN-LAST:event_checkActionPerformed

    private void check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check1ActionPerformed
        boolean isSelected = check1.isSelected();

        if (isSelected) {
            Cpass.setEchoChar((char)0);
        } else {
            Cpass.setEchoChar('*');
        }
    }//GEN-LAST:event_check1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
      String username = un.getText();  
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your username.");
        return;
    }

    // Create a database connection
    dbConnect db = new dbConnect();  // Instantiate dbConnector
    Connection con = db.getConnection(); // Get connection

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
        return;
    }

    try {
        PreparedStatement stmt = con.prepareStatement(
            "SELECT security_question, security_answer FROM tbl_accounts WHERE u_username = ?"
        );
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            sq.removeAllItems();
            sq.addItem(rs.getString("security_question"));
            sq.setEnabled(true);
          correctAnswer = rs.getString("security_answer"); // still okay if hashed

            Submit.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }
        
        fetchSecurityQuestion();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
    } finally {
        try {
            if (con != null) {
                con.close(); // Close the connection after use
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgetPass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Cpass;
    private javax.swing.JPanel Main;
    private javax.swing.JPasswordField Newpass;
    private javax.swing.JLabel Submit;
    private javax.swing.JLabel acc_id1;
    private javax.swing.JLabel acc_id2;
    private javax.swing.JLabel acc_id3;
    private javax.swing.JTextField ans;
    private javax.swing.JCheckBox check;
    private javax.swing.JCheckBox check1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel logout;
    private javax.swing.JComboBox<String> sq;
    private javax.swing.JPanel submit;
    private javax.swing.JTextField un;
    // End of variables declaration//GEN-END:variables
}
