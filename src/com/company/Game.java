package com.company;

import com.company.Combat.Battle;
import com.company.Commands.CommandGroup;
import com.company.Commands.CommandParser;
import com.company.Entities.PlayerEntity;
import com.company.Entities.Skeleton;
import com.company.Entities.Troll;
import com.company.Items.Item;
import com.company.Map.LevelData;
import com.company.Map.MapGenerator;
import com.company.Map.Room;

import java.awt.*;
import java.util.Collection;

public class Game {

    private PlayerEntity playerData;
    private LevelData currentLevelData;
    private CommandGroup gameCommands;

    public void startNewGame(){
        createNewLevel();
        initialisePlayer();
        initialiseCommands();
        runGame();
    }
    private void createNewLevel(){
        currentLevelData = new LevelData();
        currentLevelData.setLevelMap(MapGenerator.createNewMap(15));
        currentLevelData.addMonster(new Skeleton(1),5);
        currentLevelData.addMonster(new Troll(1),1);
    }
    private void initialisePlayer(){
        playerData = new PlayerEntity();
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
        currentLevelData.displayCurrentRoomData();
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
        Room room = currentLevelData.getRoomAt(movePoint);
        if(room == null){
            System.out.println("There is no room there");
            return;
        }
        System.out.println("-------------------------------");
        System.out.println("You move to the " + direction);
        System.out.println("-------------------------------");
        currentLevelData.moveToRoom(room);
        onMoveRoom();
    }
    private void onMoveRoom(){
        //Do this whenever room has been changed
        //TODO check if room has a monster and start a battle
        if(currentLevelData.getCurrentRoom().hasMonster()){
            //Start battle
            Battle battle = new Battle(playerData,currentLevelData.getCurrentRoom().getMonster());
            battle.doBattle();
        }
    }

    /**
     * Get a point in the direction 'direction'
     * @param direction The string to read
     * @return A point in the direction 'direction' or null if direction could not be read
     */
    private Point getPointInDirection(String direction){
        Point currentPoint = currentLevelData.getCurrentRoom().getRoomPosition();
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
        if(currentLevelData.getCurrentRoom().hasItem()){
            Collection<Item> itemsInRoom = currentLevelData.getCurrentRoom().getItemInventory();
            Item itemToTake = CommandParser.parseItemCommand(object,itemsInRoom);
            if(currentLevelData.getCurrentRoom().transferItemTo(itemToTake,playerData)){
                return;
            }
        }
        System.out.println("No item with name " + object + " found.");
    }
    private void dropItem(String item){
        Item itemToDrop = CommandParser.parseItemCommand(item,playerData.getItemInventory());
        if(itemToDrop != null){
            playerData.transferItemTo(itemToDrop,currentLevelData.getCurrentRoom());
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
