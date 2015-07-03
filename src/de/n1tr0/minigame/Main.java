/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.n1tr0.minigame;

import java.awt.Color;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabi
 */
public class Main extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Main
     */
    //Create objects
    public Player player1 = new Player();
    public Enemy enemy = new Enemy();
    public Laserbeam laser = new Laserbeam();
    private boolean hit = false;

    public Main() {
        int x = (int) (Math.random() * -5 + 25);
        System.out.println(x);
        initComponents();

        //Life at start
        System.out.println("Player1 has " + player1.getHealth() + " life left");

        //Draw the player's ship
        getContentPane().add(player1);
        player1.setLocation(50, 100);
        player1.setText("Player1");

        //Draw the enemy's ship
        getContentPane().add(enemy);
        enemy.setText("X");
        enemy.setLocation(350, 100);

        //Test the lasers
        getContentPane().add(laser);
        laser.setLocation(player1.getX(), 100);
        laser.setEnabled(false);

        //Player1 listens to (Click-)Action
        player1.addActionListener(this);

        //Start enemy movement
        
        Thread enemyMove = new Thread() {
            public void run() {
                while (true){
                while (enemy.getPositiony()>0) {
                    
                    enemy.moveTop();
                }
                
                while (enemy.getPositiony()<200)    
                    enemy.moveBot();
                
            }
                }
        };
        
        enemyMove.start();
    }

    // Says what happens when an action is performed
    @Override

    public void actionPerformed(ActionEvent e) {
        Thread moveThread = new Thread() {
            public void run() {
                for (int i = 0; i < 25; i++) {

                    laser.moveRight();
                    laser.sprayY();
                    checkHit();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        moveThread.start();

    }

    private void checkHit() {
        //Get location of laser and enemy and test if hit
        //Hit on top
        if (laser.getPositionx() >= enemy.getPositionx() && laser.getPositiony() >= enemy.getPositiony()) {
            hit = true;
        }

        //Hit on bottom
        if (laser.getPositionx() >= enemy.getPositionx() && laser.getPositiony() <= (enemy.getPositiony() + enemy.getHeight())) {
            hit = true;
        }

        //Missed top
        if (laser.getPositionx() >= enemy.getPositionx() && laser.getPositiony() < enemy.getPositiony()) {
            System.out.println("Target Missed!");
            hit = false;
            laser.resetPosx();
            laser.newSprayY();
        }

        //Missed bottom
        if (laser.getPositionx() >= enemy.getPositionx() && laser.getPositiony() > (enemy.getPositiony() + enemy.getHeight())) {
            System.out.println("Target Missed!");
            laser.newSprayY();
            hit = false;
            laser.resetPosx();
        }

        if (hit) {
            laser.resetPosx();
            System.out.println("Enemy Hit!");
            laser.getPositionx();
            laser.newSprayY();

            enemy.setHealth(-3);
            hit = false;
            if (enemy.health <= 3) {
                enemy.setBackground(Color.red);
            }
            if (enemy.health <= 6 && enemy.health > 3) {
                enemy.setBackground(Color.yellow);
            }
        }
        if (enemy.getHealth() <= 0) {
            System.out.println("Enemy defeated!");
            hit = false;
            enemy.setVisible(hit);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
