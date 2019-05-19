package com.company;

import java.io.IOException;
import java.util.*;

/**
 * Handles the game menu and initialises a game
 */
public class GameLauncher {

    private static final Map<String,Command> menuCommands = new HashMap<>();
    private static final List<String> commandOrder = new ArrayList<>();
    private boolean running;

    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    public void initialise(){
        running = true;
        initialiseMenuCommands();
        displayMenu();
        while(running){
            String command = waitForInput();
            try{
                menuCommands.get(CommandParser.parseMenuCommand(command,commandOrder)).doCommand();
            }
            catch(IOException e){
                System.out.println("Command not recognised. Please try again");
            }
        }
    }

    private void startNewGame(){
        System.out.println("Starting new game");
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
        addCommand("new game",this::startNewGame);
        //TODO uncomment this if got enough time to implement saving
//        addCommand("continue",this::continueGame);
        addCommand("exit",this::exit);
    }
    private void addCommand(String command,Command method){
        menuCommands.put(command,method);
        commandOrder.add(command);
    }

    private void displayMenu(){
        System.out.println("Welcome to Zork!");
        for(int i = 0; i < commandOrder.size(); i++){
            String command = commandOrder.get(i);
            System.out.println(i+1 + ". " + command.substring(0,1).toUpperCase() + command.substring(1));
        }
    }
    private String waitForInput(){
        return INPUT_SCANNER.nextLine();
    }
    private void exit(){
        System.out.println("Exiting game");
        running = false;
    }
    public static Set<String> getValidCommands(){
        return menuCommands.keySet();
    }
}
