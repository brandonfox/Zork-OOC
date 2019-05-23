package com.company;

import Commands.CommandGroup;
import Commands.CommandPair;
import Commands.CommandParser;

import java.io.IOException;
import java.util.*;

/**
 * Handles the game menu and initialises a game
 */
public class GameLauncher {

    private CommandGroup menuCommands;
    public static boolean GAME_RUNNING;

    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    public void initialise(){
        GAME_RUNNING = true;
        initialiseMenuCommands();
        displayMenu();
        while(GAME_RUNNING){
            String command = waitForInput();
            try {
                CommandPair cmdPair = CommandParser.parseCommand(command, menuCommands);
                menuCommands.getCommand(cmdPair.getCommand()).doCommand(cmdPair.getParam());
            }
            catch(IOException e){
                System.out.println("Command not recognised. Please try again");
            }
        }
    }

    private void startNewGame(){
        System.out.println("Starting new game");
        Game game = new Game();
        game.startNewGame();
        //TODO implement starting new game stuff
    }
    private void continueGame(){
        //TODO implement continue game function
    }

    /**
     * Initialise all menu commands.
     * When adding new commands add them in this function
     * Commands are automatically numbered in the order they are given
     */
    private void initialiseMenuCommands(){
        menuCommands = new CommandGroup();
        menuCommands.addCommand("new game",(param) -> startNewGame());
        //TODO uncomment this if got enough time to implement saving
//        menuCommands.addCommand("continue",(param) -> continueGame());
        menuCommands.addCommand("exit",(param) -> exit());
    }

    private void displayMenu(){
        System.out.println("Welcome to Zork!");
        menuCommands.printCommands();
    }
    private String waitForInput(){
        return INPUT_SCANNER.nextLine();
    }
    public static void exit(){
        System.out.println("Exiting game");
        GAME_RUNNING = false;
    }
}
