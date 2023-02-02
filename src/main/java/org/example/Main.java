package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean playing = true;
        boolean playerDied = false;
        boolean npcDied = false;
        int turn = 0;
        int round = 0;

        while (playing) {
            System.out.println(Colors.ANSI_BLUE + "\n\nX=================================================================X\n" + Colors.ANSI_RESET);
            System.out.println("Welcome to the scary dungeon! We have been waiting for you.\n");
            System.out.println(Colors.ANSI_BLUE + "X=================================================================X\n" + Colors.ANSI_RESET);
            System.out.println("Who is this brave man? What is your name?");

            // Generate the characters and weapons
            Weapon chainsaw = new Weapon("Chainsaw", 18);
            GameCharacter player = new Player(input.nextLine(), 100, 0.8, chainsaw);
            player.addInventory(chainsaw);
            GameCharacter npc = Npc.spawnNpc();

            // Prints out the players info
            System.out.printf("%s\nX============================== YOU ==============================X\n%s", Colors.ANSI_BLUE, Colors.ANSI_RESET);
            System.out.printf("Your name: %s", player.getName());
            System.out.printf("\nYour HP: %s%,.0f%s", Colors.ANSI_GREEN, player.getHitPoints(), Colors.ANSI_RESET);
            System.out.printf("\nYour current weapon: %s%s%s", Colors.ANSI_PURPLE, player.getEquippedWeapon().getName(), Colors.ANSI_RESET);
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
                round++;
                System.out.printf("%sX============================ %sROUND %s%s ============================X%s\n", Colors.ANSI_BLUE, Colors.ANSI_CYAN, round, Colors.ANSI_BLUE, Colors.ANSI_RESET);
                System.out.printf("\nA scary %s%s%s appears in front of you!", Colors.ANSI_RED, npc.getName(), Colors.ANSI_RESET);
                System.out.printf("\n\nAttack %s(Enter)%s Flee %s(q)%s", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                if (input.nextLine().equals("q")) {
                    playing = false;
                    System.out.println("\nYou ran away!\n");
                    break fightLoop;
                }

                attackLoop:
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
                        player.addInventory(player.getEquippedWeapon());
                        // TODO PICK WEAPON FROM INVENTORY
                        // TODO REMOVE WEAPON FROM INVENTORY
                        player.getInventory().forEach(weapon -> {
                            System.out.println(weapon.getName());
                        });
                        System.out.printf("\nAttack %s(Enter)%s Flee %s(q)%s\n", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                        if (input.nextLine().equals("q")) {
                            playing = false;
                            System.out.println("\nYou ran away!\n");
                            break fightLoop;
                        }
                    }

                    // Attacks
                    // Critical hits
                    Random rnd = new Random();
                    int critHit;
                    if (rnd.nextInt(6) == 3) {
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
                    System.out.printf("\n%s%s was defeated!%s\n\n", Colors.ANSI_GREEN, npc.getName(), Colors.ANSI_RESET);
                    System.out.printf("%sX=================================================================X\n%s", Colors.ANSI_BLUE, Colors.ANSI_RESET);
                    System.out.printf("\nYou patch up your wounds, you now have %s%s%s HP!\n", Colors.ANSI_GREEN, player.Heal(), Colors.ANSI_RESET);
                    System.out.printf("\nContinue walking forward %s(Enter)%s or Flee %s(q)%s\n", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                    if (input.nextLine().equals("q")) {
                        playing = false;
                        System.out.println("\nYou ran away!\n");
                    }
                    turn = 0;
                    npc = Npc.spawnNpc();
                    npcDied = false;
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
                System.out.printf("Play again %s(Enter)%s Quit %s(q)%s.", Colors.ANSI_GREEN, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
                if (input.nextLine().equals("q")) {
                    System.out.println("Quitting the game. Thanks for playing!");
                    input.close();
                    System.exit(0);
                    break;
                } else {
                    playing = true;
                    round = 0;
                    turn = 0;
                    playerDied = false;
                    npcDied = false;
                }
            }
        }
    }
}