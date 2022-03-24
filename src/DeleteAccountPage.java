import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author mohammadali
 */
public class DeleteAccountPage extends javax.swing.JFrame {

    /**
     * Creates new form DeleteAccountPage
     */
    public DeleteAccountPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JButton delete = new javax.swing.JButton();
        // Variables declaration - do not modify//GEN-BEGIN:variables
        javax.swing.JButton back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DeleteAccount");
        setResizable(false);

        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Are You Sure?");

        delete.setFont(new Font("Tahoma", Font.BOLD, 18)); // NOI18N
        delete.setText("YES");
        delete.addActionListener(this::deleteActionPerformed);

        back.setText("Back");
        back.addActionListener(this::BackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(delete))
                                        .addComponent(back))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(back)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        ProfilePage pf = new ProfilePage();
        pf.show();
        this.dispose();
    }//GEN-LAST:event_BackActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            //Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://your ip/Database Name","UserName","Password");
            String sql = "DELETE FROM user WHERE username=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "DELETE ACCOUNT SUCCESSFULL");
            HomePage hp = new HomePage();
            hp.show();
            this.dispose();
        } catch (ClassNotFoundException | SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "خطا");
        }
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "DELETE FROM post WHERE username=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "DELETE FROM comment WHERE username1=? OR username2=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.setString(2, HomePage.USERNAME);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "DELETE FROM follow WHERE username1=? OR username2=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.setString(2, HomePage.USERNAME);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "DELETE FROM likepost WHERE username1=? OR username2=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.setString(2, HomePage.USERNAME);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "DELETE FROM privatemessage WHERE username1=? OR username2=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.setString(2, HomePage.USERNAME);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_deleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(DeleteAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(() -> new DeleteAccountPage().setVisible(true));
    }

    // End of variables declaration//GEN-END:variables
}