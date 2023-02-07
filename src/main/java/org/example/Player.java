package org.example;

import java.io.Serializable;

public class Player extends GameCharacter implements Serializable {
    public Player(String name, int hitPoints, double dexterity, Weapon weapon) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.dexterity = dexterity;
        this.setEquippedWeapon(weapon);
    }
}
