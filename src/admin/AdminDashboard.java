/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Startups.Login;
import java.awt.Color;
import config.Session;
import config.dbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class AdminDashboard extends javax.swing.JFrame {

    private Color H;
    Color h = new Color(51,51,255);
    private Color D;
    Color d = new Color(240,240,240);
    public AdminDashboard() {
        initComponents();
        loadUserProfile();
    }
    
    
    public void logEvent(int userId, String username, String action) {
        dbConnect dbc = new dbConnect();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;
        Timestamp time = new Timestamp(new Date().getTime());

        try {
            String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) "
                    + "VALUES ('" + userId + "', '" + username + "', '" + time + "', '" + action + "')";
            pstmt = con.prepareStatement(sql);

            /*            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.setString(4, userType);*/
            pstmt.executeUpdate();
            System.out.println("Login log recorded successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
    }
    
    private void loadLogs() {
    dbConnect connector = new dbConnect();
    try (Connection con = connector.getConnection()) {

        // If you have statuses, and want to update something, adjust the query accordingly
        // For example, if you have a status column and want to update from 'Pending' to 'Active'
        // But your columns don't show status, so skip or update as needed

        // Fetch logs - change column names to match your table
        String selectQuery = "SELECT l.log_id, l.u_username, l.action_time, " +
                             "l.log_action, u.u_username AS user_exists " +
                             "FROM tbl_logs l LEFT JOIN tbl_accounts u ON l.u_username = u.u_username " +
                             "ORDER BY l.action_time DESC";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Log ID", "Username", "Action Time", "Log Action", "User Status"}, 0
            );

            while (rs.next()) {
                String userStatus = (rs.getString("user_exists") == null) ? "Invalid User" : "Valid User";

                model.addRow(new Object[]{
                    rs.getInt("log_id"),
                    rs.getString("u_username"),
                    rs.getTimestamp("action_time"),
                    rs.getString("log_action"),
                    userStatus
                });
            }

            logstbl.setModel(model);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error loading logs: " + ex.getMessage());
    }
}
    
    private void loadUserProfile() {
    dbConnect dbc = new dbConnect();
    Session sess = Session.getInstance();

    String query = "SELECT u_image FROM tbl_accounts WHERE u_id = ?";

    try (Connection conn = dbc.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setInt(1, sess.getUid());
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String imagePath = rs.getString("u_image");

            if (imagePath != null && !imagePath.isEmpty()) {
                ImageIcon icon = new ImageIcon(imagePath);
                u_image.setIcon(icon);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Log the error
        JOptionPane.showMessageDialog(this, "Error loading profile image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Navigation = new javax.swing.JPanel();
        logout = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        acc_fname = new javax.swing.JLabel();
        acc_lname = new javax.swing.JLabel();
        acc_uname = new javax.swing.JLabel();
        acc_type = new javax.swing.JLabel();
        acc_phone = new javax.swing.JLabel();
        acc_id = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        u_image = new javax.swing.JLabel();
        users1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        users = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        users2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        users5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logstbl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Main.setBackground(new java.awt.Color(0, 102, 102));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(102, 102, 102));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admin Dashboard");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 30, 1210, 40));

        Main.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 100));

        Navigation.setBackground(new java.awt.Color(153, 255, 204));
        Navigation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        logout.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Logout");
        logout.add(jLabel10);
        jLabel10.setBounds(0, 10, 130, 22);

        Navigation.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 493, 130, 40));

        acc_fname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_fname.setForeground(new java.awt.Color(255, 255, 255));
        acc_fname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_fname.setText("First Name");
        Navigation.add(acc_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 300, 30));

        acc_lname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_lname.setForeground(new java.awt.Color(255, 255, 255));
        acc_lname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_lname.setText("Last Name");
        Navigation.add(acc_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 300, 30));

        acc_uname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_uname.setForeground(new java.awt.Color(255, 255, 255));
        acc_uname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_uname.setText("User Name");
        Navigation.add(acc_uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 300, 30));

        acc_type.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_type.setForeground(new java.awt.Color(255, 255, 255));
        acc_type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_type.setText("Type");
        Navigation.add(acc_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 300, 30));

        acc_phone.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_phone.setForeground(new java.awt.Color(255, 255, 255));
        acc_phone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_phone.setText("Phone");
        Navigation.add(acc_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 300, 30));

        acc_id.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        acc_id.setForeground(new java.awt.Color(255, 255, 255));
        acc_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id.setText("ID");
        Navigation.add(acc_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 300, 30));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(u_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 0, 200, 170));

        Navigation.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 220, 170));

        Main.add(Navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 300, 540));

        users1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users1MouseExited(evt);
            }
        });
        users1.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Change Pass");
        users1.add(jLabel14);
        jLabel14.setBounds(0, 10, 160, 30);

        Main.add(users1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 160, 50));

        users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usersMouseExited(evt);
            }
        });
        users.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Users");
        users.add(jLabel12);
        jLabel12.setBounds(0, 10, 160, 30);

        Main.add(users, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 160, 50));

        users2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users2MouseExited(evt);
            }
        });
        users2.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("PaymentForm");
        users2.add(jLabel15);
        jLabel15.setBounds(0, 7, 160, 30);

        Main.add(users2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 160, 50));

        users5.setBackground(new java.awt.Color(255, 255, 255));
        users5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                users5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users5MouseExited(evt);
            }
        });
        users5.setLayout(null);

        jLabel19.setBackground(new java.awt.Color(0, 102, 102));
        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("ViewParking");
        users5.add(jLabel19);
        jLabel19.setBounds(0, 10, 160, 30);

        Main.add(users5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 160, 50));

        logstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "log_id", "u_id", "u_username", "action_time", "log_action"
            }
        ));
        jScrollPane1.setViewportView(logstbl);

        Main.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 650, -1));

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ADMIN DETAILS");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 160, 20));

        Main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 160, 60));

        getContentPane().add(Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        dbConnect connector = new dbConnect();
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        int userId = 0;
        String uname = null;
        try {
            String query2 = "SELECT * FROM tbl_accounts WHERE u_id = '" + sess.getUid() + "'";
            PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
                uname = resultSet.getString("u_username");
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }

        logEvent(userId, uname, "Logged Out");
        Login sp = new Login();
        sp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(h);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(d);
    }//GEN-LAST:event_logoutMouseExited

    private void usersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersMouseClicked
       Session sess = Session.getInstance();

if (!sess.getType().equalsIgnoreCase("Admin")) {
    JOptionPane.showMessageDialog(null, "Access Denied. Only Admins can access this section.", "Unauthorized", JOptionPane.WARNING_MESSAGE);
    return;
}

U_Admin ua = new U_Admin();
ua.setVisible(true);
this.dispose();

    }//GEN-LAST:event_usersMouseClicked

    private void usersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersMouseEntered
        users.setBackground(h);
    }//GEN-LAST:event_usersMouseEntered

    private void usersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersMouseExited
        users.setBackground(d);
    }//GEN-LAST:event_usersMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Session sess = Session.getInstance();
       if(sess.getUid() == 0)
       {
           JOptionPane.showMessageDialog(null,"No Account, Login FIrst");
           Login l = new Login();
           l.setVisible(true);
           this.dispose();
       }else
       {
           acc_fname.setText("" + sess.getFname());
           acc_lname.setText("" + sess.getLname());
           acc_uname.setText("" + sess.getUname());
           acc_type.setText("" + sess.getType());
           acc_phone.setText("" + sess.getPhone());
           acc_id.setText("" + sess.getUid());
       }
       
       loadLogs();
    }//GEN-LAST:event_formWindowActivated

    private void users1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users1MouseClicked
   Session sess = Session.getInstance();

if (!sess.getType().equalsIgnoreCase("Admin")) {
    JOptionPane.showMessageDialog(null, "Access Denied. Only Admins can access this section.", "Unauthorized", JOptionPane.WARNING_MESSAGE);
    return;
}
        
    Admin_ChangePass acp = new Admin_ChangePass();
    acp.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_users1MouseClicked

    private void users1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users1MouseEntered
        users1.setBackground(h);
    }//GEN-LAST:event_users1MouseEntered

    private void users1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users1MouseExited
        users1.setBackground(d);
    }//GEN-LAST:event_users1MouseExited

    private void users2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users2MouseClicked
      
    }//GEN-LAST:event_users2MouseClicked

    private void users2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users2MouseEntered
        users2.setBackground(h);
    }//GEN-LAST:event_users2MouseEntered

    private void users2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users2MouseExited
        users2.setBackground(d);
    }//GEN-LAST:event_users2MouseExited

    private void users5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users5MouseClicked
        ParkingForm PF = new ParkingForm();
        PF.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_users5MouseClicked

    private void users5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users5MouseEntered
        users5.setBackground(h);
    }//GEN-LAST:event_users5MouseEntered

    private void users5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users5MouseExited
        users5.setBackground(d);
    }//GEN-LAST:event_users5MouseExited

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
           AdminDetails ad = new AdminDetails();
        ad.setVisible(true);
        this.dispose();  // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel Navigation;
    private javax.swing.JLabel acc_fname;
    private javax.swing.JLabel acc_id;
    private javax.swing.JLabel acc_lname;
    private javax.swing.JLabel acc_phone;
    private javax.swing.JLabel acc_type;
    private javax.swing.JLabel acc_uname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel logout;
    private javax.swing.JTable logstbl;
    private javax.swing.JLabel u_image;
    private javax.swing.JPanel users;
    private javax.swing.JPanel users1;
    private javax.swing.JPanel users2;
    private javax.swing.JPanel users5;
    // End of variables declaration//GEN-END:variables
}
