package org.example;

import java.util.ArrayList;
import java.util.Random;

abstract class GameCharacter {
    public String name;
    public int hitPoints;
    private Weapon equippedWeapon;
    double dexterity;
    GameCharacter defender;
    int damageAmount;
    int critHit;
    Random rnd = new Random();

    public ArrayList<Weapon> inventory = new ArrayList<>();

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
        this.defender = defender;
        this.critHit = critHit;
            return damageAmount = defender.takeDamage((int) (getEquippedWeapon().getDamage() * getDexterity() + critHit + rnd.nextInt(3)));
    }

    public int Heal() {
        Random rnd = new Random();
        int healAmount = rnd.nextInt(60)+(int) this.getHitPoints();
        if (healAmount > 100) healAmount = 100;
        this.setHitPoints(healAmount);
        return (int) this.getHitPoints();
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

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public void addInventory(Weapon weapon) {
        inventory.add(weapon);
    }
}
