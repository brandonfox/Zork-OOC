package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    private PlayerData playerData;
    private LevelMap currentLevelMap;
    private CommandGroup gameCommands;

    private Scanner scanner;

    public void startNewGame(){
        scanner = new Scanner(System.in);
        createNewLevel();
        initialisePlayer();
        initialiseCommands();
        runGame();
    }
    private void createNewLevel(){
        currentLevelMap = MapGenerator.createNewMap(15);
    }
    private void initialisePlayer(){
        playerData = new PlayerData();
    }
    private void runGame(){
        //TODO place introductory messages
        System.out.println("Welcome to zork where you'll die over and over and over again.");
        System.out.println("Have fun");
        while(GameLauncher.GAME_RUNNING){
            displayCurrentRoomData();
            gameCommands.printCommandsInline();
            String command = waitForInput();
            try {
                CommandPair cmdPair = CommandParser.parseCommand(command, gameCommands);
                gameCommands.getCommand(cmdPair.getCommand()).doCommand(cmdPair.getParam());
            }catch(IOException e){
                System.out.println("Command not recognised.");
            }
        }
    }
    private void displayCurrentRoomData(){
        currentLevelMap.displayCurrentRoomData();
    }
    private String waitForInput(){
        return scanner.nextLine();
    }
    private void initialiseCommands(){
        gameCommands = new CommandGroup();
        gameCommands.addCommand("info",(param) -> displayInfo());
        gameCommands.addCommand("move",this::moveRoom);
        gameCommands.addCommand("take",this::take);
        gameCommands.addCommand("drop",this::dropItem);
        gameCommands.addCommand("attack with",this::attack);
        gameCommands.addCommand("help",(param) -> help());
        gameCommands.addCommand("quit",(param) -> quit());
    }
    private void displayInfo(){
        //TODO implement info display
    }
    private void moveRoom(String direction){

    }
    private void take(String object){
        //TODO implement take stuff
    }
    private void dropItem(String item){
        //TODO implement dropping stuff
    }
    private void attack(String weapon){
        //TODO implement attacking
    }
    private void help(){
        //TODO implement help
    }
    private void quit(){
        GameLauncher.exit();
    }
}
