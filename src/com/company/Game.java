package com.company;

import com.company.Commands.CommandGroup;
import com.company.Commands.CommandParser;
import com.company.Items.Item;
import com.company.Map.LevelMap;
import com.company.Map.MapGenerator;
import com.company.Map.Room;

import java.awt.*;
import java.util.Collection;

public class Game {

    private PlayerData playerData;
    private LevelMap currentLevelMap;
    private CommandGroup gameCommands;

    public void startNewGame(){
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
        //Print an introductory message before starting the game
        System.out.println("Welcome to zork where you'll die over and over and over again.");
        System.out.println("Have fun");
        while(GameLauncher.GAME_RUNNING){
            displayCurrentRoomData();
            gameCommands.printCommandsInline();
            CommandParser.getAndExecuteCommand(gameCommands);
        }
    }
    private void displayCurrentRoomData(){
        currentLevelMap.displayCurrentRoomData();
    }
    private void initialiseCommands(){
        gameCommands = new CommandGroup();
        gameCommands.addCommand("info",this::displayInfo);
        gameCommands.addCommand("move",this::moveRoom);
        gameCommands.addCommand("take",this::take);
        gameCommands.addCommand("drop",this::dropItem);
        gameCommands.addCommand("attack with",this::attack);
        gameCommands.addCommand("help",(param) -> help());
        gameCommands.addCommand("quit",(param) -> quit());
    }
    private void displayInfo(String category){
        //TODO implement info display
    }
    private void moveRoom(String direction){
        Point movePoint = getPointInDirection(direction);
        if(movePoint == null){
            System.out.println("Direction: " + direction + " not recognised");
            return;
        }
        Room room = currentLevelMap.getRoomAt(movePoint);
        if(room == null){
            System.out.println("There is no room there");
            return;
        }
        System.out.println("-------------------------------");
        System.out.println("You move to the " + direction);
        System.out.println("-------------------------------");
        currentLevelMap.setCurrentRoom(room);
        onMoveRoom();
    }
    private void onMoveRoom(){
        //Do this whenever room has been changed
    }

    /**
     * Get a point in the direction 'direction'
     * @param direction The string to read
     * @return A point in the direction 'direction' or null if direction could not be read
     */
    private Point getPointInDirection(String direction){
        Point currentPoint = currentLevelMap.getCurrentRoom().getRoomPosition();
        if(direction.contains("north"))
            return new Point(currentPoint.x,currentPoint.y + 1);
        else if(direction.contains("east"))
            return new Point(currentPoint.x + 1, currentPoint.y);
        else if(direction.contains("south"))
            return new Point(currentPoint.x,currentPoint.y - 1);
        else if(direction.contains("west"))
            return new Point(currentPoint.x-1,currentPoint.y);
        else
            return null;

    }
    private void take(String object){
        if(currentLevelMap.getCurrentRoom().hasItem()){
            Collection<Item> itemsInRoom = currentLevelMap.getCurrentRoom().getItemInventory();
            Item itemToTake = CommandParser.parseItemCommand(object,itemsInRoom);
            if(currentLevelMap.getCurrentRoom().transferItemTo(itemToTake,playerData)){
                return;
            }
        }
        System.out.println("No item with name " + object + " found.");
    }
    private void dropItem(String item){
        Item itemToDrop = CommandParser.parseItemCommand(item,playerData.getItemInventory());
        if(itemToDrop != null){
            playerData.transferItemTo(itemToDrop,currentLevelMap.getCurrentRoom());
        }
        else{
            System.out.println("You dont have an item called " + item);
        }
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
