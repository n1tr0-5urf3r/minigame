package de.n1tr0.minigame;

import javax.swing.JButton;

/**
 *
 * @author Fabi
 */
public class Enemy extends JButton {

    public int health;
    private final int width = 70;
    private final int height = 50;
    private double PosY = this.getPositiony();

    public Enemy() {
        super();
        init();
    }

    private void init() {
        health = 10;
        this.setBounds(width, height, width, height);

    }

    public void setHealth(int health) {
        this.health = this.health + health;
    }

    public int getHealth() {
        return this.health;
    }

    public int getPositionx() {
        int current_x = this.getX();
        return current_x;
    }

    public int getPositiony() {
        int current_y = this.getY();
        return current_y;
    }

    public void moveBot() {
        PosY = PosY + 0.000001;
        this.setLocation(this.getPositionx(), (int) PosY);
    }

    public void moveTop() {
        PosY = PosY - 0.000001;
        this.setLocation(this.getPositionx(), (int) PosY);
    }

}
