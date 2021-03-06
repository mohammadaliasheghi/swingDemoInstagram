import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author mohammadali
 */
public class UserAccount extends JFrame {

    /**
     * Creates new form UserAccount
     */
    public static String USER;

    public UserAccount(String username) {
        initComponents();
        USER = username;
        user.setText(username);
        int numberOfpost = 0;
        int numberOffollowers = 0;
        int numberOffollowing = 0;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "Select txt,date From post Where username=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, USER);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            while (rs.next()) {
                numberOfpost++;
                numberPost.setText(Integer.toString(numberOfpost));
                model.addRow(new Object[]{rs.getString("txt"), rs.getString("date")});
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        //Followers Number
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad")) {
                String sql = "Select * From follow Where username2=?";
                PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
                ps.setString(1, USER);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    numberOffollowers++;
                    Followers.setText(Integer.toString(numberOffollowers));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        //Following Number
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad")) {
                String sql = "Select * From follow Where username1=?";
                PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
                ps.setString(1, USER);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    numberOffollowing++;
                    Following.setText(Integer.toString(numberOffollowing));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad")) {
                String sql = "Select * From follow Where username1=? And username2=?";
                PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
                ps.setString(1, HomePage.USERNAME);
                ps.setString(2, USER);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    FollowButton.setVisible(false);
                } else {
                    unFollow.setVisible(false);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FollowButton = new JButton();
        unFollow = new JButton();
        JButton sendMessage = new JButton();
        // Variables declaration - do not modify//GEN-BEGIN:variables
        JButton back = new JButton();
        JLabel jLabel1 = new JLabel();
        JLabel fmp = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        user = new JLabel();
        Followers = new JLabel();
        Following = new JLabel();
        numberPost = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        table = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UserAccountPage");
        setResizable(false);

        FollowButton.setText("Follow");
        FollowButton.addActionListener(this::FollowButtonActionPerformed);

        unFollow.setText("unFollow");
        unFollow.addActionListener(this::unFollowActionPerformed);

        sendMessage.setText("SendMessage");
        sendMessage.addActionListener(this::sendMessageActionPerformed);

        back.setText("Back");
        back.addActionListener(this::BackActionPerformed);

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Username");

        fmp.setHorizontalAlignment(SwingConstants.CENTER);
        fmp.setText("Followers");

        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Following");

        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("PostValue");

        user.setHorizontalAlignment(SwingConstants.CENTER);

        Followers.setHorizontalAlignment(SwingConstants.CENTER);
        Followers.setText("0");

        Following.setHorizontalAlignment(SwingConstants.CENTER);
        Following.setText("0");

        numberPost.setHorizontalAlignment(SwingConstants.CENTER);
        numberPost.setText("0");

        table.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "TextPost", "PostDate"
                }
        ) {
            final Class[] types = new Class[]{
                    String.class, Object.class
            };
            final boolean[] canEdit = new boolean[]{
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked();
            }
        });
        jScrollPane1.setViewportView(table);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(fmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(user, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(numberPost, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                                .addComponent(Following, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(Followers, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(sendMessage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(unFollow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(FollowButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(back, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(user, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(Followers, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                                        .addComponent(fmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(Following, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(numberPost, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(FollowButton, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(unFollow, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(sendMessage, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(back, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        ProfilePage pf = new ProfilePage();
        pf.show();
        this.dispose();
    }//GEN-LAST:event_BackActionPerformed

    private void FollowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FollowButtonActionPerformed
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad")) {
                String sql = "Insert Into Follow Values(?,?)";
                PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
                ps.setString(1, HomePage.USERNAME);
                ps.setString(2, USER);
                ps.executeUpdate();

                FollowButton.setVisible(false);
                unFollow.setVisible(true);
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UserAccount usr = new UserAccount(USER);
        usr.show();
        this.dispose();
    }//GEN-LAST:event_FollowButtonActionPerformed

    private void unFollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unFollowActionPerformed
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad")) {
                String sql = "Delete From follow Where username1=? And username2=?";
                PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
                ps.setString(1, HomePage.USERNAME);
                ps.setString(2, USER);
                ps.executeUpdate();

                FollowButton.setVisible(true);
                unFollow.setVisible(false);
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UserAccount usr = new UserAccount(USER);
        usr.show();
        this.dispose();
    }//GEN-LAST:event_unFollowActionPerformed

    private void sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageActionPerformed
        PrivateMessagePage pv = new PrivateMessagePage(HomePage.USERNAME, USER);
        pv.show();
        this.dispose();
    }//GEN-LAST:event_sendMessageActionPerformed

    private void tableMouseClicked() {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int selectedRow = table.getSelectedRow();
        String post = model.getValueAt(selectedRow, 0).toString();
        String date = model.getValueAt(selectedRow, 1).toString();
        postPage pp = new postPage(USER, post, date);
        pp.show();
        this.dispose();
    }//GEN-LAST:event_tableMouseClicked

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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UserAccount(USER).setVisible(true));
    }

    private JButton FollowButton;
    private JLabel Followers;
    private JLabel Following;
    private JLabel numberPost;
    private JTable table;
    private JButton unFollow;
    private JLabel user;
    // End of variables declaration//GEN-END:variables
}
