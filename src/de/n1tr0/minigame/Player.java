/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.n1tr0.minigame;

import javax.swing.JButton;

/**
 *
 * @author Fabi
 */
public class Player extends JButton {

    public int health;
    private final int width = 70;
    private final int height = 50;

    public Player() {
        super();
        init();

    }

    private void init() {
        health = 100;
        this.setBounds(width, height, width, height);

    }

    public void setHealth(int health) {
        this.health = this.health + health;
    }

    public int getHealth() {
        return this.health;
    }

}
