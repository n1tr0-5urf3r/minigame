/* 
 * Copyright (C) 2015 Fabi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.n1tr0.minigame;

import java.awt.Color;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Fabi
 */
public class Main extends javax.swing.JFrame implements ActionListener, KeyListener {

    /**
     * Creates new form Main
     */
    //Create objects
    public Player player1 = new Player();
    public hud hud = new hud();
    private final hud EnemyHealth = new hud();
    private final hud PlayerHealth = new hud();
    public Enemy enemy = new Enemy();
    public Laserbeam laser = new Laserbeam();
    public Laserbeam enemyLaser = new Laserbeam();
    private final ImageIcon Laserbeam_red = new ImageIcon("src/resources/laser_red.png");
    private final ImageIcon Laserbeam_green = new ImageIcon("src/resources/laser_green.png");
    private final ImageIcon PlayerShip = new ImageIcon("src/resources/playership.png");
    private final ImageIcon EnemyShip = new ImageIcon("src/resources/enemyship.png");
    private boolean enemyDefeated = false;
    

    // Inititalize Variables
    private boolean hit = false;
    //private double attackSpeed = 1.0;

    public Main() {
        setContentPane(new JLabel(new ImageIcon("src/resources/background.png")));
        initComponents();
        //Life at start
        System.out.println("Player1 has " + player1.getHealth() + " life left");

        // Initialize Objects
        initPlayer();
        initHUD();
        initEnemy();
        initLasers();
        enemyMovement();
        initEnemyLasers();

        //Player1 listens to (Click-)Actions
        player1.addActionListener(this);
        player1.setFocusable(true);
        enemy.addKeyListener(this);
    }

    // Says what happens when an action is performed
    @Override

    // MouseAction for Players shot
    public void actionPerformed(ActionEvent e) {
        shoot();

    }

    // Check hitboxes of Enemy and set colors according to Health
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
            hud.setText("Target Missed");
            hit = false;
            laser.resetPosx(player1.getX(), player1.getY());
            laser.newSprayY();
        }

        //Missed bottom
        if (laser.getPositionx() >= enemy.getPositionx() && laser.getPositiony() > (enemy.getPositiony() + enemy.getHeight())) {
            System.out.println("Target Missed!");
            hud.setText("Target Missed");
            laser.newSprayY();
            hit = false;
            laser.resetPosx(player1.getX(), player1.getY());
        }

        if (hit) {
            laser.resetPosx(player1.getX(), player1.getY());
            System.out.println("Enemy Hit!");
            hud.setText("Enemy hit");
            laser.getPositionx();
            laser.newSprayY();

            enemy.setHealth(-3);
            hit = false;
            if (enemy.health <= 3) {
                EnemyHealth.setBackground(Color.red);
            }
            if (enemy.health <= 6 && enemy.health > 3) {
                EnemyHealth.setBackground(Color.yellow);
            }
        }
        if (enemy.getHealth() <= 0) {
            System.out.println("Enemy defeated!");
            hud.setText("Enemy defeated");
            enemyDefeated = true;
            EnemyHealth.setBackground(Color.black);
            hit = false;
            enemy.setVisible(hit);

        }

    }

    // Check hitboxes of Player and set colors according to Health
    private void checkPlayerGotHit() {

        //Get location of laser and enemy and test if hit
        //Hit on top
        if (enemyLaser.getPositionx() <= player1.getX() + player1.getWidth() && enemyLaser.getPositiony() >= player1.getY() && enemyLaser.getY() <= (player1.getY() + player1.getHeight())) {
            hit = true;
        }

        //Hit on bottom
        if (enemyLaser.getPositionx() <= player1.getX() + player1.getWidth() && enemyLaser.getPositiony() <= (player1.getY() + player1.getHeight()) && enemyLaser.getPositiony() >= player1.getY()) {
            hit = true;
        }

        //Missed top
        if (enemyLaser.getPositionx() <= player1.getX() && enemyLaser.getPositiony() < player1.getY()) {
            hit = false;
            enemyLaser.resetEnemyPos(enemy.getX(), enemy.getY());
        }

        //Missed bottom
        if (enemyLaser.getPositionx() <= player1.getX() && enemyLaser.getPositiony() > (player1.getY() + player1.getHeight())) {
            hit = false;
            enemyLaser.resetEnemyPos(enemy.getX(), enemy.getY());
        }

        if (hit) {
            enemyLaser.resetEnemyPos(enemy.getX(), enemy.getY());
            System.out.println("You got hit!");
            hud.setText("You got hit!");
            laser.getPositionx();

            player1.setHealth(-2);
            hit = false;
            if (player1.health <= 15) {
                PlayerHealth.setBackground(Color.yellow);
            }
            if (player1.health <= 8 && player1.health > 1) {
                PlayerHealth.setBackground(Color.red);
            }
        }
        if (player1.getHealth() <= 0) {
            System.out.println("You got defeated");
            hud.setText("You got defeated");
            PlayerHealth.setBackground(Color.black);
            hit = false;
        }

    }

    // Enemyship will move in y direction continously, if Healthbar movement activated, you can't control your ship anymore (bug #1)
    private void enemyMovement() {
        Thread enemyMove = new Thread() {
            public void run() {
                while (true) {
                    while (enemy.getPositiony() > 0) {
                        enemy.moveTop();
                        //EnemyHealth.moveTop();
                    }

                    while (enemy.getPositiony() < 400) {
                        enemy.moveBot();
                        //EnemyHealth.moveBot();
                    }

                }
            }
        };
        enemyMove.start();
    }

    // Player Ship
    private void initPlayer() {
        //Draw the player's ship
        getContentPane().add(player1);
        player1.setLocation(50, 100);
        player1.setIcon(PlayerShip);
        player1.setDisabledIcon(PlayerShip);
        System.out.println("Initialized Player1");

    }

    // Enemy Ship
    private void initEnemy() {
        getContentPane().add(enemy);
        enemy.setLocation(320, 100);
        enemy.setIcon(EnemyShip);
        enemy.setDisabledIcon(EnemyShip);
        System.out.println("Initialized Enemy");
    }

    // Healthbars for Enemy and Player, Statusbar in lower middle
    private void initHUD() {
        getContentPane().add(hud);
        getContentPane().add(PlayerHealth);
        getContentPane().add(EnemyHealth);

        hud.setFocusable(false);
        PlayerHealth.setFocusable(false);
        EnemyHealth.setFocusable(false);

        hud.setBounds(200, 30, 200, 30);
        hud.setLocation(110, 450);

        PlayerHealth.setLocation(player1.getX(), player1.getY() - 10);
        PlayerHealth.setBackground(Color.green);

        EnemyHealth.setLocation(280, enemy.getY() - 10);
        EnemyHealth.setBackground(Color.green);

        System.out.println("Initialized HUD");
    }

    // Players Lasers with Attack Speed 1, start not visible, green laser icon
    private void initLasers() {
        getContentPane().add(laser);
        laser.setLocation(player1.getX(), player1.getY());
        laser.setEnabled(false);
        laser.setAttackSpeed(1.0);
        laser.setVisible(false);
        laser.setDisabledIcon(Laserbeam_green);
        System.out.println("Initialized Lasers");

    }

    // Enemy Lasers with Attack Speed1, red icon and start not visible.
    private void initEnemyLasers() {
        getContentPane().add(enemyLaser);
        enemyLaser.setLocation(enemy.getX(), enemy.getY());
        enemyLaser.setEnabled(false);
        enemyLaser.setAttackSpeed(1.0);
        enemyLaser.setDisabledIcon(Laserbeam_red);
        System.out.println("Initialized Enemy Lasers");
        enemyShot();

    }

    // Enemy Lasershot, will shot while enemy is alive, sets Laser to visble. Laser goes to players position when it's released. 
    // Afterwards reset to Enemy Position
    private void enemyShot() {
        Thread enemyShot = new Thread() {
            public void run() {
                while (!enemyDefeated) {
                    enemyLaser.setVisible(true);
                    for (int i = 0; i < 50; i++) {
                        enemyLaser.shootEnemyLaser(enemy.getX(), enemy.getY());
                        checkPlayerGotHit();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    enemyLaser.setVisible(false);
                    enemyLaser.resetEnemyPos(enemy.getX(), enemy.getY());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        enemyShot.start();

    }

    // Players shot, is commenced when Keylistener or Mouselistener activated, stepsToenemy calculates the distance needed to the enemy for hitting.
    private void shoot() {

        // Initialize Lasers with AttackSpeed 1.0
        // AttackSpeed will be handed over to the Laser and make Steps of 12 multiplied with attackSpeed
        double stepsToEnemy = (enemy.getX() - player1.getX()) / (12 * laser.getAttackSpeed());
        laser.setVisible(true);

        // Position from where the laser will shoot, if commented and Position in for loop set to getY(), Laser will follow when ship is moving
        // Maybe implement upgrade for aiming later (if clause)
        double currentPosYPlayer = player1.getY();

        Thread moveThread = new Thread() {
            public void run() {
                for (int i = 0; i < stepsToEnemy; i++) {
                    player1.setEnabled(false);
                    laser.shootLaser(player1.getX(), currentPosYPlayer);
                    laser.sprayY();
                    checkHit();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                player1.setEnabled(true);
            }
        };
        moveThread.start();
    }

    public void keyTyped(KeyEvent e) {
        // Invoked when a key has been typed.
    }

    // Arrow Up for Up
    // Arrow Down for Down
    // Space for shooting
    // Left/Right currently disabled (buggy)
    @Override
    public void keyPressed(KeyEvent e) {
        // Invoked when a key has been pressed.
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            player1.moveRight();
//            laser.stickToPlayer(player1.getX(), player1.getY());
//        }
//        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            player1.moveLeft();
//            laser.stickToPlayer(player1.getX(), player1.getY());
//
//        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player1.moveUp();
            PlayerHealth.moveUp();
            //laser.stickToPlayer(player1.getX(), player1.getY());

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player1.moveDown();
            PlayerHealth.moveDown();
            //laser.stickToPlayer(player1.getX(), player1.getY());

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (laser.laserIsActive()) {
                hud.setText("Laser is cooling down");
            } else {
                shoot();
            }

        }
    }

    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
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
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
