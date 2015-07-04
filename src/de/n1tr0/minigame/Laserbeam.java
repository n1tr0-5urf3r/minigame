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

    double x = 50;
    private final int width = 20;
    private final int height = 20;
    int randY = (int) (Math.random()*40+1);
   
  

    public Laserbeam() {
        super();
        init();
    }

    public void init() {
        this.setBounds(width, height, width, height);
        
    }

    public void moveRight(double attackSpeed) {

        x = (x+12*attackSpeed);
            this.setLocation((int) x, 100);  
    }
    
    public void sprayY(){
          
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

    public void resetPosx() {
        x=50;
        this.setLocation(50, 100);
    }
    
    public void newSprayY(){
        randY = (int) (Math.random()*40+1);
    }

}
