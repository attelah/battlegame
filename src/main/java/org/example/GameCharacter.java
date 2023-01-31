package org.example;

import java.util.Random;

abstract class GameCharacter {
    public String name;
    public int hitPoints;
    private Weapon equippedWeapon;
    double dexterity;
    GameCharacter defender;
    int damageAmount;
    int critHit;


    public GameCharacter(String name, int hitPoints, Weapon equippedWeapon) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = equippedWeapon;
        this.dexterity = dexterity;
    }

    public int takeDamage(int damage) {
        this.hitPoints -= damage;
        if (this.hitPoints < 0) this.hitPoints = 0;
        return damage;
    }

    public double attack(GameCharacter defender, int critHit) {
        Random rnd = new Random();
        this.defender = defender;
        this.critHit = critHit;
            return damageAmount = defender.takeDamage((int) (getEquippedWeapon().getDamage() * getDexterity() + critHit + rnd.nextInt(3)));
    }

    protected GameCharacter() {
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public String getName() {
        return name;
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
