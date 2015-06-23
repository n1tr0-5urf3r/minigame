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

    int x = 150;
    private final int width = 20;
    private final int height = 20;

    public Laserbeam() {
        super();
        init();
    }

    public void init() {
        this.setBounds(width, height, width, height);
    }

    public void moveRight() {

        x = x + 20;
            this.setLocation(x, 100);  
    }
    
    public void sprayY(){
        
        int randY = (int) (Math.random()*90+75);
        this.setLocation(this.getX(), randY);
     
    }

    public int getPositionx() {
        int current_x = this.getX();
        return current_x;
    }
    
    public int getPositiony() {
        int current_y = this.getY();
        return current_y;
    }

    public void resetPosx() {
        x =150;
        this.setLocation(150, 100);
    }

}
