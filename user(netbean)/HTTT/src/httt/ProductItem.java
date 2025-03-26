/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package httt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Icon;

/**
 *
 * @author user
 */
public class ProductItem extends javax.swing.JPanel {

    private boolean selected;
    public ProductItem(Model_ProductItem data) {
        initComponents();
        setOpaque(false);
        AddCart_btn.setText("Thêm vào giỏ");
        AddCart_btn.setBackground(Color.decode("#00B4DB"));
        Image.setIcon(data.toImage());
        Name.setText(data.getTitle());
        PriceTag.setText(String.valueOf(data.getPrice()));
//        if(data.getType()==Model_ProductItem.ProductType.SACH){
//            Image.setIcon(data.toImage());
//            Name.setText(data.getTitle());
//            PriceTag.setText(String.valueOf(data.getPrice()));
//        }else if(data.getType()==Model_ProductItem.ProductType.VO){
//            Image.setIcon(data.toImage());
//            Name.setText(data.getTitle());
//            PriceTag.setText(String.valueOf(data.getPrice()));
//        }else{
//            lbName.setText(" ");
//        }
    }
    
    public void setSelected(boolean Selected) {
        this.selected = Selected;
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new httt.PanelBorder();
        Image = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        PriceTag = new javax.swing.JLabel();
        AddCart_btn = new httt.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(200, 300));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(200, 300));
        setLayout(null);

        panelBorder1.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(panelBorder1);
        panelBorder1.setBounds(10, 10, 180, 140);

        Name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Name.setText("Name");
        Name.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(Name);
        Name.setBounds(10, 160, 180, 60);

        PriceTag.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PriceTag.setForeground(new java.awt.Color(255, 102, 102));
        PriceTag.setText("Price");
        add(PriceTag);
        PriceTag.setBounds(10, 220, 180, 30);

        AddCart_btn.setText("myButton1");
        AddCart_btn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        add(AddCart_btn);
        AddCart_btn.setBounds(31, 260, 130, 30);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#cfdef3"), 0, getHeight(), Color.decode("#e0eafc"));
//        g2.setPaint(gradient);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
    }
    
//    private Rectangle getAutoSize(Icon image){
//        int w = 140;
//        int h = 140;
//        int iw = image.getIconWidth();
//        int ih = image.getIconHeight();
//        double xscale = (double) iw / w;
//        double yscale = (double) ih / h;
//        double scale = Math.max(xscale, yscale);
//        int width = (int)(scale*iw);
//        int height = (int)(scale*ih);
//        int x = (w-width)/2;
//        int y = (h-height)/2;
//        
//        return new Rectangle(new Point(x,y), new Dimension(width, height));
//    }
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private httt.MyButton AddCart_btn;
    private javax.swing.JLabel Image;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel PriceTag;
    private httt.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
