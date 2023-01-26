package org.example;

public class Player extends GameCharacter{
    public Player(String name, int hitPoints, double dexterity, Weapon weapon) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.dexterity = dexterity;
        this.setEquippedWeapon(weapon);
    }
}
