package org.example;

public class Main {
    public static void main(String[] args) {
        // Generate the characters
        GameCharacter player = new Player("Player", 100, 0.8);
        GameCharacter npc = new Npc("Bot", 50, 0.5);

        System.out.println("Let the Battle Begin");
        System.out.println("A scary-looking Ghoul runs towards you. You decide to attack.");


            while(player.getHitPoints() > 0 && npc.getHitPoints() > 0) {
                // Fight
                if (player.getHitPoints() > 0) {
                    System.out.printf("Player attacks Ghoul for 20 HP. Ghoul has %d HP left\n", npc.takeDamage(20));
                }
                if (npc.getHitPoints() > 0) {
                    System.out.printf("Ghoul attacks Player for 20 HP. Player has %d HP left\n", player.takeDamage(20));
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