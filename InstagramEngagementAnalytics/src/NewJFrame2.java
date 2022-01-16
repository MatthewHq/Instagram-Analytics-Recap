
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Portalz
 */
public class NewJFrame2 extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame2
     */
    int numOfPosts = main.orderedPosts.size();
    HashMap<String, JLabel> likedPostLink = new HashMap();

    private javax.swing.JLabel latestLikeLabel;

    public NewJFrame2() {
//        initComponents();
        javax.swing.JLabel[] iconLabels = new javax.swing.JLabel[numOfPosts];
        javax.swing.JLabel[] likedLabels = new javax.swing.JLabel[numOfPosts];
        javax.swing.JLabel[] dateLabel = new javax.swing.JLabel[numOfPosts];
        for (int i = 0; i < numOfPosts; i++) {
            iconLabels[i] = new javax.swing.JLabel();
            iconLabels[i].setText("iL " + i);

            likedLabels[i] = new javax.swing.JLabel();
            likedLabels[i].setText("ltl " + i);

            dateLabel[i] = new javax.swing.JLabel();
            dateLabel[i].setText("dl " + i);
        }

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        latestLikeLabel = new javax.swing.JLabel();
        likeCountLabel = new javax.swing.JLabel();
        earliestLikeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 200, 40));

        likeCountLabel.setText("Like Count:");
        jPanel1.add(likeCountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 200, 30));

        earliestLikeLabel.setText("Earliest Like: ");
        jPanel1.add(earliestLikeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 200, 30));

        latestLikeLabel.setText("Latest Like: ");
        jPanel1.add(latestLikeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 200, 30));

        int[] icon = {120, 210, 200, 200};
        int[] date = {350, 350, 100, 50};
        int[] liked = {350, 210, 100, 50};

        int yHop = 300;
        int xHop = 500;

        int repeat = numOfPosts / 2;
        for (int i = 0; i < repeat; i++) {
            BufferedImage img1 = null;
            BufferedImage img2 = null;

            try {

                img1 = ImageIO.read(new File((System.getProperty("user.dir") + File.separator + "pics" + File.separator) + main.posts.get(main.orderedPosts.get(i * 2)).getPostCode() + ".jpg"));
                img2 = ImageIO.read(new File((System.getProperty("user.dir") + File.separator + "pics" + File.separator) + main.posts.get(main.orderedPosts.get(i * 2 + 1)).getPostCode() + ".jpg"));
                Image newImage1 = img1.getScaledInstance(200, 200, Image.SCALE_FAST);
                Image newImage2 = img2.getScaledInstance(200, 200, Image.SCALE_FAST);
                ImageIcon imgIcon1 = new ImageIcon(newImage1);
                ImageIcon imgIcon2 = new ImageIcon(newImage2);
                iconLabels[i * 2].setIcon(imgIcon1);
                iconLabels[i * 2 + 1].setIcon(imgIcon2);

                java.util.Date time1 = new java.util.Date((long) ((int) Integer.parseInt(main.posts.get(main.orderedPosts.get(i * 2)).getTimestamp())) * 1000);
                java.util.Date time2 = new java.util.Date((long) ((int) Integer.parseInt(main.posts.get(main.orderedPosts.get(i * 2 + 1)).getTimestamp())) * 1000);
                String pattern = "MMM dd yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                dateLabel[i * 2].setText(simpleDateFormat.format(time1));
                dateLabel[i * 2 + 1].setText(simpleDateFormat.format(time2));

                jPanel1.add(iconLabels[i * 2], new org.netbeans.lib.awtextra.AbsoluteConstraints(icon[0], icon[1] + (yHop * i), icon[2], icon[3]));
                jPanel1.add(iconLabels[i * 2 + 1], new org.netbeans.lib.awtextra.AbsoluteConstraints(icon[0] + xHop, icon[1] + (yHop * i), icon[2], icon[3]));

                jPanel1.add(likedLabels[i * 2], new org.netbeans.lib.awtextra.AbsoluteConstraints(liked[0], liked[1] + (yHop * i), liked[2], liked[3]));
                jPanel1.add(likedLabels[i * 2 + 1], new org.netbeans.lib.awtextra.AbsoluteConstraints(liked[0] + xHop, liked[1] + (yHop * i), liked[2], liked[3]));

                jPanel1.add(dateLabel[i * 2], new org.netbeans.lib.awtextra.AbsoluteConstraints(date[0], date[1] + (yHop * i), date[2], date[3]));
                jPanel1.add(dateLabel[i * 2 + 1], new org.netbeans.lib.awtextra.AbsoluteConstraints(date[0] + xHop, date[1] + (yHop * i), date[2], date[3]));

            } catch (Exception e) {
                System.out.println("test");
            }
            likedPostLink.put(main.posts.get((main.orderedPosts.get(i * 2))).getId(), likedLabels[i * 2]);
            likedPostLink.put(main.posts.get((main.orderedPosts.get(i * 2 + 1))).getId(), likedLabels[i * 2 + 1]);
        }
        jScrollPane1.setViewportView(jPanel1);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        likeCountLabel = new javax.swing.JLabel();
        earliestLikeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Date");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 50, 30));

        jLabel2.setText("Icon");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 200, 170));

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 230, 40));

        jLabel4.setText("Liked");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 50, 30));

        likeCountLabel.setText("Like Count:");
        jPanel1.add(likeCountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 70, 30));

        earliestLikeLabel.setText("Earliest Like: ");
        jPanel1.add(earliestLikeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 70, 30));

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (evt.getKeyCode() == 10) {
            int likeCount = 0;
            boolean flag = true;
            String earliestLike = "";
            for (String id : likedPostLink.keySet()) {
                if (main.likes.containsKey(id)) {
                    for (Person p : main.likes.get(id).people.values()) {
                        if (p.getUsername().equals(jTextField1.getText())) {
                            try {
                                BufferedImage img1;
                                img1 = ImageIO.read(new File((System.getProperty("user.dir") + File.separator + "green.jpg")));
                                ImageIcon imgIcon1 = new ImageIcon(img1);
                                likedPostLink.get(id).setIcon(imgIcon1);
                                likedPostLink.get(id).setText((main.likes.get(id).getCount() - p.getOrderFromTop()) + "");
                                likedPostLink.get(id).setFont(new Font(likedPostLink.get(id).getName(), Font.BOLD, 20));
                                likeCount++;
                                earliestLike = main.posts.get(id).getTimestamp();

                                if (flag) {
                                    java.util.Date time1 = new java.util.Date((long) ((int) Integer.parseInt(
                                            earliestLike)) * 1000
                                    );
                                    String pattern = "MMM dd yyyy";
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                                    latestLikeLabel.setText("Latest Like: " + simpleDateFormat.format(time1));
                                    flag = false;
                                }
//                                 likedPostLink.get(id).setText("YO WTF AOWINFWAOIFNAWOIFNAWOIFAWNFOIAWNFAOIWNF");
                            } catch (IOException ex) {
                                Logger.getLogger(NewJFrame2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            likeCountLabel.setText("Like Count: " + likeCount);
                            java.util.Date time1 = new java.util.Date((long) ((int) Integer.parseInt(
                                    earliestLike)) * 1000
                            );
                            String pattern = "MMM dd yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                            earliestLikeLabel.setText("Earliest Like: " + simpleDateFormat.format(time1));
                        }
                    }
                }
            }
        } else if (evt.getKeyCode() == 27) {
            clearAll();
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void clearAll() {
        latestLikeLabel.setText("Latest Like: ");
        earliestLikeLabel.setText("Earliest Like: ");
        likeCountLabel.setText("Like Count: ");
        jTextField1.setText("");
        for (JLabel x : likedPostLink.values()) {
            x.setText("");
            x.setIcon(null);
        }

    }

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
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel earliestLikeLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel likeCountLabel;
    // End of variables declaration//GEN-END:variables
}
