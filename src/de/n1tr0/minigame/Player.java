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

import javax.swing.JRadioButton;

/**
 *
 * @author Fabi
 */
public class Player extends JRadioButton {

    public int health;
    private final int width = 70;
    private final int height = 50;

    public Player() {
        super();
        init();

    }

    private void init() {
        health = 20;
        this.setBounds(width, height, width, height);

    }

    public void setHealth(int health) {
        this.health = this.health + health;
    }

    public int getHealth() {
        return this.health;
    }

    public void moveRight() {
        this.setLocation(this.getX() + 20, this.getY());
    }

    public void moveLeft() {
        this.setLocation(this.getX() - 20, this.getY());
    }

    public void moveUp() {
        this.setLocation(this.getX(), this.getY() - 10);
    }

    public void moveDown() {
        this.setLocation(this.getX(), this.getY() + 10);
    }

}
