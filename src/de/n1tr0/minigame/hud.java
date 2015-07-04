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

    private final int width = 100;
    private final int height = 30;

    public hud() {
        super();
        init();
    }

    public void init() {
        this.setBounds(width, height, width, height);
    }
}
