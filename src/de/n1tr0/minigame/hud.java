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

import javax.swing.JTextArea;

/**
 *
 * @author Fabi
 */
public class hud extends JTextArea {

    private final int width = 70;
    private final int height = 10;
    private double PosY = this.getY();
    public boolean isAlive = true;

    public hud() {
        super();
        init();
    }

    public void init() {
        this.setBounds(width, height, width, height);
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void moveUp() {
        if (isAlive) {
            this.setLocation(this.getX(), this.getY() - 10);
        }
    }

    public void moveDown() {
        if (isAlive) {
            this.setLocation(this.getX(), this.getY() + 10);
        }
    }

    public void moveBot() {
        if (isAlive) {
            PosY = PosY + 0.000003;
            this.setLocation(this.getX(), (int) PosY);
        }
    }

    public void moveTop() {
        if (isAlive) {
            PosY = PosY - 0.000003;
            this.setLocation(this.getX(), (int) PosY);
        }
    }

    public void moveRight() {
        if (isAlive) {
            this.setLocation(this.getX() + 20, this.getY());
        }
    }

    public void moveLeft() {
        if (isAlive) {
            this.setLocation(this.getX() - 20, this.getY());
        }
    }
}
