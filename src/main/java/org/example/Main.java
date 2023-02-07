package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean playing = true;
        boolean playerDied = false;
        boolean npcDied = false;
        int turn = 2;

        while (true) {

            System.out.println(Colors.ANSI_BLUE + "\n\nX=================================================================X\n" + Colors.ANSI_RESET);
            System.out.println("Welcome to the scary dungeon! We have been waiting for you.\n");
            System.out.println(Colors.ANSI_BLUE + "X=================================================================X\n" + Colors.ANSI_RESET);

            GameCharacter player;

            try {
                player = (Player) FileUtils.loadObject("game.save");
                System.out.printf("Found a saved game, do you want to load the save? %sy%s/%sn%s\n", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                if (input.nextLine().equals("n")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Started a new save.\n");
                // Generate the characters and weapons
                System.out.println("Who is this brave man? What is your name?");
                Weapon chainsaw = new Weapon("Chainsaw", 18);
                player = new Player(input.nextLine(), 100, 0.8, chainsaw);
                player.addInventory(player.getEquippedWeapon());
            }

            GameCharacter npc = Npc.spawnNpc();

            // Prints out the players info
            System.out.printf("%s\nX============================== YOU ==============================X\n%s", Colors.ANSI_BLUE, Colors.ANSI_RESET);
            System.out.printf("Your name: %s", player.getName());
            System.out.printf("\nYour HP: %s%,.0f%s", Colors.ANSI_GREEN, player.getHitPoints(), Colors.ANSI_RESET);
            System.out.printf("\nYour current weapon: %s%s%s (%s%s%s)", Colors.ANSI_PURPLE, player.getEquippedWeapon().getName(), Colors.ANSI_RESET, Colors.ANSI_RED, player.getEquippedWeapon().getDamage(), Colors.ANSI_RESET);
            System.out.printf("\n%sX=================================================================X\n\n%s", Colors.ANSI_BLUE, Colors.ANSI_RESET);

            // Ready?
            System.out.printf("Start walking forward %s(Enter)%s or Flee %s(q)%s\n", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
            if (input.nextLine().equals("q")) {
                playing = false;
                System.out.println("\nYou ran away!\n");
            }

            // The fight
            fightLoop:
            while (playing && !playerDied) {
                System.out.printf("%sX============================ %sROUND %s%s ============================X%s\n", Colors.ANSI_BLUE, Colors.ANSI_CYAN, player.getRound(), Colors.ANSI_BLUE, Colors.ANSI_RESET);
                System.out.printf("\nA scary %s%s%s %s(%,.0f)%s appears in front of you!\n", Colors.ANSI_RED, npc.getName(), Colors.ANSI_RESET, Colors.ANSI_GREEN, npc.getHitPoints(), Colors.ANSI_RESET);

                while (player.getHitPoints() > 0 && npc.getHitPoints() > 0) {
                    // Count the turns
                    turn++;

                    // Switch the roles
                    GameCharacter attacker = player;
                    GameCharacter defender = npc;
                    GameCharacter temp;

                    if (turn == 2) {
                        temp = attacker;
                        attacker = defender;
                        defender = temp;
                    }

                    //Ask what the player wants to do in between of the attacks
                    if (turn == 3) {
                        turn = 1;
                        System.out.printf("\n%sX===== Inventory =====X%s\n", Colors.ANSI_BLUE, Colors.ANSI_RESET);
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            System.out.printf("%s - %s%s%s (%s%s%s)\n", i + 1, Colors.ANSI_PURPLE, player.getInventory().get(i).getName(), Colors.ANSI_RESET, Colors.ANSI_RED, player.getInventory().get(i).getDamage(), Colors.ANSI_RESET);
                        }

                        System.out.printf("\n\nChoose a weapon for attack %s(1-%s)%s or flee %s(q)%s\n", Colors.ANSI_GREEN, player.getInventory().size(), Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                        Boolean valid = false;
                        while (!valid) {
                            String choice;
                            try {
                                choice = input.nextLine();
                                if (choice.equals("q")) {
                                    playing = false;
                                    System.out.println("\nYou ran away!\n");
                                    break fightLoop;
                                } else {
                                    player.setEquippedWeapon(player.getInventory().get(Integer.parseInt(choice) - 1));
                                }
                                valid = true;
                            } catch (Exception e) {
                                System.out.printf("\n%sInvalid choice!%s\n", Colors.ANSI_RED, Colors.ANSI_RESET);
                            }
                        }

                    }

                    // Attacks
                    // Critical hits
                    Random rnd = new Random();
                    int critHit;
                    if (rnd.nextInt(7) == 3) {
                        System.out.printf("%sCRITICAL HIT!%s\n", Colors.ANSI_RED, Colors.ANSI_RESET);
                        critHit = 5;
                    } else {
                        critHit = 0;
                    }
                    System.out.printf("%s attacks %s with a %s%s%s for %s%,.0f%s HP.\n -> %s has %s%,.0f%s HP left\n", attacker.getName(), defender.getName(), Colors.ANSI_PURPLE, attacker.getEquippedWeapon().getName(), Colors.ANSI_RESET, Colors.ANSI_GREEN, attacker.attack(defender, critHit), Colors.ANSI_RESET, defender.getName(), Colors.ANSI_GREEN, defender.getHitPoints(), Colors.ANSI_RESET);


                    if (player.getHitPoints() == 0) {
                        playerDied = true;
                    }
                    if (npc.getHitPoints() == 0) {
                        npcDied = true;
                    }
                }

                // If NPC dies
                if (npcDied) {
                    player.addRound();
                    System.out.printf("\n%s%s was defeated!%s\n\n", Colors.ANSI_GREEN, npc.getName(), Colors.ANSI_RESET);
                    System.out.printf("%sX=================================================================X\n%s", Colors.ANSI_BLUE, Colors.ANSI_RESET);
                    System.out.printf("\nDo you want to pick up %ss dropped weapon?\n%s%s%s (%s%s%s) %sy%s/%sn%s?\n", npc.getName(), Colors.ANSI_PURPLE, npc.getEquippedWeapon().getName(), Colors.ANSI_RESET, Colors.ANSI_GREEN, npc.getEquippedWeapon().getDamage(), Colors.ANSI_RESET, Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                    if (input.nextLine().equals("y")) {
                        if (player.getInventory().size() > 2) {
                            System.out.printf("\n%sX===== Inventory =====X%s\n", Colors.ANSI_BLUE, Colors.ANSI_RESET);
                            for (int i = 0; i < player.getInventory().size(); i++) {
                                System.out.printf("%s - %s%s%s (%s%s%s)\n", i + 1, Colors.ANSI_PURPLE, player.getInventory().get(i).getName(), Colors.ANSI_RESET, Colors.ANSI_RED, player.getInventory().get(i).getDamage(), Colors.ANSI_RESET);
                            }
                            System.out.printf("\n%sYour inventory is full!%s Select a weapon to drop %s(1-%s)%s\n", Colors.ANSI_RED, Colors.ANSI_RESET, Colors.ANSI_GREEN, player.getInventory().size(), Colors.ANSI_RESET);
                            Boolean valid = false;
                            while (!valid) {
                                String choice = input.nextLine();
                                try {
                                    Integer.parseInt(choice);
                                    System.out.printf("\nYou threw away a %s", player.getInventory().get(Integer.parseInt(choice) - 1).getName());
                                    player.removeInventory(player.getInventory().get(Integer.parseInt(choice) - 1));
                                    valid = true;
                                } catch (Exception e) {
                                    System.out.printf("\n%sInvalid choice!%s\n", Colors.ANSI_RED, Colors.ANSI_RESET);
                                }
                            }
                        }
                        player.addInventory(npc.getEquippedWeapon());
                        System.out.printf("\nYou picked up %ss dropped weapon.\n", npc.getName());
                    }
                    System.out.printf("\nYou patch up your wounds, you now have %s%s%s HP!\n", Colors.ANSI_GREEN, player.heal(), Colors.ANSI_RESET);
                    System.out.printf("\nContinue walking forward %s(Enter)%s or Flee %s(q)%s\n", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                    if (input.nextLine().equals("q")) {
                        playing = false;
                        System.out.println("\nYou ran away!\n");
                    } else {
                        turn = 2;
                        npc = Npc.spawnNpc();
                        npcDied = false;
                    }
                }
            }

            // When player dies, ask if he wants to play again or quit.
            if (playerDied) {
                System.out.printf("\n%s%s was defeated!%s\n\n", Colors.ANSI_RED, player.getName(), Colors.ANSI_RESET);
                System.out.printf("%sGAME OVER!%s\n\n", Colors.ANSI_RED, Colors.ANSI_RESET);
                playing = false;
            }

            // Quitting the program
            if (!playing) {
                System.out.printf("%sX=================================================================X\n\n%s", Colors.ANSI_BLUE, Colors.ANSI_RESET);
                System.out.printf("Play again %s(Enter)%s Quit %s(q)%s.\n", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                if (input.nextLine().equals("q")) {
                    System.out.printf("Do you want to save the game? %sy%s/%sn%s\n", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                    if (input.nextLine().equals("y")) {
                        FileUtils.saveObject(player, "game.save");
                        System.out.printf("%sGame saved!%s\n", Colors.ANSI_GREEN, Colors.ANSI_RESET);
                    }
                    System.out.println("Quitting the game. Thanks for playing!");
                    input.close();
                    System.exit(0);
                } else {
                    player.resetRounds();
                    turn = 2;
                    playerDied = false;
                    npcDied = false;
                    playing = true;
                }
            }
        }
    }
}