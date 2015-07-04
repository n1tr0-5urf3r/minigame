/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.n1tr0.minigame;

import javax.swing.JTextArea;

/**
 *
 * @author Fabi
 */
public class hud extends JTextArea {

    private final int width = 70;
    private final int height = 10;
    private double PosY = this.getY();

    public hud() {
        super();
        init();
    }

    public void init() {
        this.setBounds(width, height, width, height);
    }

    public void moveUp() {
        this.setLocation(this.getX(), this.getY() - 10);
    }

    public void moveDown() {
        this.setLocation(this.getX(), this.getY() + 10);
    }

    public void moveBot() {
        PosY = PosY + 0.000001;
        this.setLocation(this.getX(), (int) PosY);
    }

    public void moveTop() {
        PosY = PosY - 0.000001;
        this.setLocation(this.getX(), (int) PosY);
    }
}
