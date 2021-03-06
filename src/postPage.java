import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author FaraDars
 */
public class postPage extends javax.swing.JFrame {

    /**
     * Creates new form postPage
     */
    static String Pusername;
    static String Ppost;
    static String Pdate;

    public postPage(String username, String post, String date) {
        initComponents();
        Pusername = username;
        Ppost = post;
        Pdate = date;
        lblDate.setText(date);
        txtPost.setText(post);
        int commentNumber = 0;
        int likeNumber = 0;

        //display comment and commentnumber
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "SELECT username1,comment FROM comment WHERE username2=? AND post=? AND date=? ORDER BY datecomment DESC";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Pusername);
            ps.setString(2, Ppost);
            ps.setString(3, Pdate);
            ResultSet rs = ps.executeQuery();

            while (jTable1.getRowCount() > 0) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(0);
            }

            while (rs.next()) {
                commentNumber++;
                lblCommentNumber.setText(Integer.toString(commentNumber));
                ((DefaultTableModel) jTable1.getModel()).addRow(new Object[]{rs.getString("username1"), rs.getString("comment")});
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        //LikeNumber
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "SELECT * FROM likepost WHERE username2=? AND post=? AND date=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Pusername);
            ps.setString(2, Ppost);
            ps.setString(3, Pdate);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                likeNumber++;
                lblLikeNumber.setText(Integer.toString(likeNumber));
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        //display image
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "SELECT * FROM post WHERE username=? AND txt=? AND date=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Pusername);
            ps.setString(2, Ppost);
            ps.setString(3, Pdate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                InputStream getImage = rs.getBinaryStream("pic");
                BufferedImage im = ImageIO.read(getImage);
                Image scaleImage = im.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), 0);
                ImageIcon icon = new ImageIcon(scaleImage);
                lblImage.setIcon(icon);
            }

        } catch (ClassNotFoundException | SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        //check like
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "SELECT * FROM likepost WHERE username1=? AND username2=? AND post=? AND date=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.setString(2, Pusername);
            ps.setString(3, Ppost);
            ps.setString(4, Pdate);
            ResultSet rs = ps.executeQuery();

            btnLike.setSelected(rs.next());

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

        lblDate = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        txtPost = new javax.swing.JTextPane();
        lblImage = new javax.swing.JLabel();
        lblLikeNumber = new javax.swing.JLabel();
        btnLike = new javax.swing.JToggleButton();
        txtComment = new javax.swing.JTextField();
        javax.swing.JButton btnComment = new javax.swing.JButton();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        lblCommentNumber = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        // Variables declaration - do not modify//GEN-BEGIN:variables
        javax.swing.JButton back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PostPage");
        setResizable(false);

        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtPost.setEnabled(false);
        txtPost.setPreferredSize(new java.awt.Dimension(193, 102));
        jScrollPane1.setViewportView(txtPost);

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setPreferredSize(new java.awt.Dimension(193, 102));

        lblLikeNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLikeNumber.setText("0");

        btnLike.setText("Like");
        btnLike.addActionListener(this::btnLikeActionPerformed);

        btnComment.setText("Send");
        btnComment.addActionListener(this::btnCommentActionPerformed);

        jLabel2.setText("Comment");

        lblCommentNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCommentNumber.setText("0");

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Username", "Comment"
                }
        ) {
            final Class[] types = new Class[]{
                    String.class, String.class
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
        jTable1.setRowHeight(30);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        back.setText("Back");
        back.addActionListener(evt -> BackActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                                .addComponent(back)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnLike, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(lblLikeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(btnComment, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(167, 167, 167)
                                                .addComponent(lblCommentNumber)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnLike)
                                        .addComponent(lblLikeNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(back))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(4, 4, 4)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(lblCommentNumber))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommentActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
            String sql = "INSERT INTO comment(username1,username2,post,date,comment) VALUES(?,?,?,?,?)";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, HomePage.USERNAME);
            ps.setString(2, Pusername);
            ps.setString(3, Ppost);
            ps.setString(4, Pdate);
            ps.setString(5, txtComment.getText());
            ps.executeUpdate();
            postPage pop = new postPage(Pusername, Ppost, Pdate);
            pop.show();
            this.dispose();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnCommentActionPerformed

    private void btnLikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLikeActionPerformed
        // TODO add your handling code here:
        if (btnLike.isSelected()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
                String sql = "INSERT INTO likepost VALUES(?,?,?,?)";
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, HomePage.USERNAME);
                ps.setString(2, Pusername);
                ps.setString(3, Ppost);
                ps.setString(4, Pdate);
                ps.executeUpdate();
                postPage pop = new postPage(Pusername, Ppost, Pdate);
                pop.show();
                this.dispose();
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad");
                String sql = "Delete FROM likepost WHERE username1=? AND username2=? AND post=? AND date=?";
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, HomePage.USERNAME);
                ps.setString(2, Pusername);
                ps.setString(3, Ppost);
                ps.setString(4, Pdate);
                ps.executeUpdate();
                postPage pop = new postPage(Pusername, Ppost, Pdate);
                pop.show();
                this.dispose();
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnLikeActionPerformed

    private void BackActionPerformed() {//GEN-FIRST:event_BackActionPerformed
        ProfilePage pf = new ProfilePage();
        pf.show();
        this.dispose();
    }//GEN-LAST:event_BackActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(postPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new postPage(Pusername, Ppost, Pdate).setVisible(true));
    }

    private javax.swing.JToggleButton btnLike;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCommentNumber;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLikeNumber;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTextPane txtPost;
    // End of variables declaration//GEN-END:variables
}
