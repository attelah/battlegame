package org.example;

public class Npc extends GameCharacter{
    public Npc(String name, int hitPoints, double dexterity, Weapon weapon) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.dexterity = dexterity;
        this.setEquippedWeapon(weapon);
    }
}
