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
public class Laserbeam extends JRadioButton {

    double x = 0;
    private final int width = 40;
    private final int height = 20;
    int randY = (int) (Math.random() * 40 + 1);
    public double AttackSpeed = 1.0;
    public boolean firstShot = true;
    public boolean firstShotP2 = true;
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
        double after;
        while (firstShot) {
            x = (startingPointX + 12 * this.AttackSpeed);
            this.setLocation((int) x, (int) playerY);
            firstShot = false;
        }
        x = (x + 12 * this.AttackSpeed);
        this.setLocation((int) x, (int) playerY);
    }

    public void shootEnemyLaser(double enemyX, double EnemyY) {
        enemy = (enemy - 12 * this.AttackSpeed);
        this.setLocation((int) this.enemy, (int) EnemyY);
    }

    public void shootPlayer2Laser(double enemyX, double enemyY) {
        double startingPointX = enemyX;
        double after;
        while (firstShotP2) {
            x = (startingPointX - 12 * this.AttackSpeed);
            this.setLocation((int) x, (int) enemyY);
            firstShotP2 = false;
        }
        x = (x + 12 * this.AttackSpeed);
        this.setLocation((int) x, (int) enemyY);
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
        this.setVisible(false);
    }

    public void resetEnemyPos(int enemyX, int enemyY) {
        this.setLocation(enemyX, enemyY);
        enemy = enemyX;
    }

    public void newSprayY() {
        randY = (int) (Math.random() * 40 + 1);
    }

    public double getAttackSpeed() {
        return AttackSpeed;
    }

    public void setAttackSpeed(double AttackSpeed) {
        this.AttackSpeed = this.AttackSpeed + AttackSpeed;
    }

    public boolean laserIsActive() {
        boolean laserActive = this.isVisible();
        return laserActive;
    }

}
