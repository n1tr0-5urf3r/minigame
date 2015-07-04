/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.n1tr0.minigame;

import javax.swing.JRadioButton;

/**
 *
 * @author Fabi
 */
public class Laserbeam extends JRadioButton {

    double x = 0;
    private final int width = 20;
    private final int height = 20;
    int randY = (int) (Math.random() * 40 + 1);
    public double AttackSpeed = 1.0;
    public boolean firstShot = true;
    private double enemy = 350;

    public Laserbeam() {
        super();
        init();
    }

    public void init() {
        this.setBounds(width, height, width, height);

    }

    public void shootLaser(double playerX, double playerY) {
        double startingPointX = playerX;
        while (firstShot) {
            x = (startingPointX + 12 * this.AttackSpeed);
            this.setLocation((int) x, (int) playerY);
            System.out.println("X1" + x);
            firstShot = false;
        }

        x = (x + 12 * this.AttackSpeed);
        System.out.println("X2 " + x);

        this.setLocation((int) x, (int) playerY);
    }

    public void shootEnemyLaser(double enemyX, double EnemyY) {
        enemy = (enemy - 12 * this.AttackSpeed);
        this.setLocation((int) this.enemy, (int) EnemyY);
    }

    public void sprayY() {

        int currentY = this.getPositiony() + randY;
        this.setLocation(this.getX(), currentY);
    }

    public int getPositionx() {
        int current_x = this.getX();
        return current_x;
    }

    public int getPositiony() {
        int current_y = this.getY();
        return current_y;
    }

    public void resetPosx(int playerX, int playerY) {
        firstShot = true;
        this.setLocation(playerX, playerY);
    }

    public void resetEnemyPos(int enemyX, int enemyY) {
        this.setLocation(enemyX, enemyY);
        enemy= enemyX;
    }

    public void newSprayY() {
        randY = (int) (Math.random() * 40 + 1);
    }

    public double getAttackSpeed() {
        return AttackSpeed;
    }

    public void setAttackSpeed(double AttackSpeed) {
        this.AttackSpeed = AttackSpeed;
    }

    public void stickToPlayer(int playerX, int playerY) {
        this.setLocation(playerX, playerY);
    }

}
