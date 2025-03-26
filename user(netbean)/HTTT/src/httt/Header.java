
package httt;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class Header extends javax.swing.JPanel {
    public Header() {
        initComponents();
        setOpaque(false);
        initIcon();
    }
    
    private void initIcon(){
        try{
            BufferedImage bfimage = ImageIO.read(new File("src\\Icon\\search.png"));
            Image image = bfimage.getScaledInstance(32, 32,Image.SCALE_SMOOTH);
            SearchIcon.setIcon(new ImageIcon(image));
            SearchIcon.setForeground(Color.WHITE);
            bfimage = ImageIO.read(new File("src\\Icon\\user.png"));
            image = bfimage.getScaledInstance(32, 32,Image.SCALE_SMOOTH);
            AccountIcon.setIcon(new ImageIcon(image));
            AccountIcon.setForeground(Color.WHITE);
            bfimage = ImageIO.read(new File("src\\Icon\\shopping-cart.png"));
            image = bfimage.getScaledInstance(32, 32,Image.SCALE_SMOOTH);
            CartIcon.setIcon(new ImageIcon(image));
            CartIcon.setForeground(Color.WHITE);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Logo_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Search_panel = new javax.swing.JPanel();
        searchText1 = new httt.SearchText();
        SearchIcon = new javax.swing.JLabel();
        Icon_panel = new javax.swing.JPanel();
        AccountIcon = new javax.swing.JLabel();
        CartIcon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setPreferredSize(new java.awt.Dimension(1000, 150));
        setLayout(null);

        Logo_panel.setOpaque(false);
        Logo_panel.setLayout(null);

        jLabel2.setText("jLabel2");
        Logo_panel.add(jLabel2);
        jLabel2.setBounds(30, 20, 170, 60);

        add(Logo_panel);
        Logo_panel.setBounds(0, 0, 180, 100);

        Search_panel.setOpaque(false);
        Search_panel.setLayout(null);

        searchText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText1ActionPerformed(evt);
            }
        });
        Search_panel.add(searchText1);
        searchText1.setBounds(50, 30, 490, 40);

        SearchIcon.setBackground(new java.awt.Color(255, 255, 255));
        SearchIcon.setForeground(new java.awt.Color(255, 255, 255));
        SearchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        Search_panel.add(SearchIcon);
        SearchIcon.setBounds(0, 30, 37, 40);

        add(Search_panel);
        Search_panel.setBounds(180, 0, 540, 100);

        Icon_panel.setOpaque(false);
        Icon_panel.setLayout(null);

        AccountIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/user.png"))); // NOI18N
        Icon_panel.add(AccountIcon);
        AccountIcon.setBounds(20, 30, 30, 40);

        CartIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/shopping-cart.png"))); // NOI18N
        Icon_panel.add(CartIcon);
        CartIcon.setBounds(180, 30, 40, 40);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Account");
        Icon_panel.add(jLabel3);
        jLabel3.setBounds(60, 40, 100, 20);

        add(Icon_panel);
        Icon_panel.setBounds(730, 0, 270, 100);
    }// </editor-fold>//GEN-END:initComponents

    private void searchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText1ActionPerformed

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#0083B0"), 0, getHeight(), Color.decode("#00B4DB"));
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        super.paintComponent(g);
    }

    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AccountIcon;
    private javax.swing.JLabel CartIcon;
    private javax.swing.JPanel Icon_panel;
    private javax.swing.JPanel Logo_panel;
    private javax.swing.JLabel SearchIcon;
    private javax.swing.JPanel Search_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private httt.SearchText searchText1;
    // End of variables declaration//GEN-END:variables
}
