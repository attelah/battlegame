package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Generate the characters and weapons
        Random rnd = new Random();
        Weapon weapon = new Weapon("Chainsaw", rnd.nextInt(12)+5);
        Weapon npcWeapon = new Weapon("Claws", rnd.nextInt(12)+5);
        GameCharacter player = new Player("Player", 100, 0.8, weapon);
        GameCharacter npc = new Npc("Bot", 100, 0.5, npcWeapon);

        System.out.println("Let the Battle Begin");
        System.out.println("A scary-looking Ghoul runs towards you. You decide to attack.");

            while(player.getHitPoints() > 0 && npc.getHitPoints() > 0) {
                // Fight
                if (player.getHitPoints() > 0) {
                    System.out.printf("Player attacks Ghoul for %,.3f HP. Ghoul has %,.3f HP left\n", player.attack(npc), npc.getHitPoints());
                }
                if (npc.getHitPoints() > 0) {
                    System.out.printf("Ghoul attacks Player for %,.3f HP. Player has %,.3f HP left\n", npc.attack(player), player.getHitPoints());
                }
                // Check if dead
                if (npc.getHitPoints() == 0) {
                    System.out.println("Ghoul is dead.\nPlayer wins!");
                }
                if (player.getHitPoints() == 0) {
                    System.out.println("Player is dead.\nGhoul wins!");
                }
            }
    }
}