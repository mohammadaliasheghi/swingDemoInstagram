import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author mohammadali
 */
public class NewPost extends javax.swing.JFrame {

    /**
     * Creates new form NewPost
     */
    public static String path;

    public NewPost() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        textPost = new javax.swing.JTextArea();
        img = new javax.swing.JLabel();
        javax.swing.JButton importImage = new javax.swing.JButton();
        // Variables declaration - do not modify//GEN-BEGIN:variables
        javax.swing.JButton back = new javax.swing.JButton();
        javax.swing.JButton sendPost = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textPost.setColumns(20);
        textPost.setRows(5);
        textPost.setPreferredSize(new java.awt.Dimension(275, 200));
        jScrollPane1.setViewportView(textPost);

        img.setBackground(new java.awt.Color(255, 255, 255));
        img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        importImage.setText("ImportImage");
        importImage.addActionListener(this::importImageActionPerformed);

        back.setText("Back");
        back.addActionListener(this::BackActionPerformed);

        sendPost.setText("Send");
        sendPost.addActionListener(this::sendPostActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(back)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sendPost)
                                                .addGap(18, 18, 18)
                                                .addComponent(importImage)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(importImage)
                                        .addComponent(back)
                                        .addComponent(sendPost))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        MyPosts mp = new MyPosts();
        mp.show();
        this.dispose();
    }//GEN-LAST:event_BackActionPerformed

    private void sendPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendPostActionPerformed
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mohammad_ali", "mohammad")) {
                InputStream is = new FileInputStream(new File(path));

                String sql = "Insert into post(username,txt,pic) values(?,?,?)";
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, HomePage.USERNAME);
                ps.setString(2, textPost.getText());
                ps.setBlob(3, is);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "SuccessFull");
                MyPosts mp = new MyPosts();
                mp.show();
                this.dispose();
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewPost.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_sendPostActionPerformed

    private void importImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "png");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String p = selectedFile.getAbsolutePath();
            path = p;
            try {
                InputStream is = new FileInputStream(p);
                BufferedImage im = ImageIO.read(is);
                Image scaleImage = im.getScaledInstance(img.getWidth(), img.getHeight(), WIDTH);
                ImageIcon icon = new ImageIcon(scaleImage);
                img.setIcon(icon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_importImageActionPerformed

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
            Logger.getLogger(NewPost.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(NewPost.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NewPost.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new NewPost().setVisible(true));
    }

    private javax.swing.JLabel img;
    private javax.swing.JTextArea textPost;
    // End of variables declaration//GEN-END:variables
}