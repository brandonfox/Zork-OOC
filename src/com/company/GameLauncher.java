package com.company;

import com.company.Commands.CommandGroup;
import com.company.Commands.CommandParser;


/**
 * Handles the game menu and initialises a game
 */
public class GameLauncher {

    private CommandGroup menuCommands;
    public static boolean GAME_RUNNING;

    public void initialise(){
        GAME_RUNNING = true;
        initialiseMenuCommands();
        displayMenu();
        while(GAME_RUNNING){
            CommandParser.getAndExecuteCommand(menuCommands);
        }
    }

    private void startNewGame(){
        System.out.println("Starting new game");
        Game game = new Game();
        game.startNewGame();
    }
    private void continueGame(){
        //TODO implement continue game function
    }

    /**
     * Initialise all menu commands.
     * When adding new commands add them in this function
     * com.company.Commands are automatically numbered in the order they are given
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
    public static void exit(){
        System.out.println("Exiting game");
        GAME_RUNNING = false;
    }
}
