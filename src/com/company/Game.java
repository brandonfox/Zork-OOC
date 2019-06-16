package com.company;

import com.company.Combat.Battle;
import com.company.Commands.CommandGroup;
import com.company.Commands.CommandParser;
import com.company.Entities.PlayerEntity;
import com.company.Items.EquippableItem;
import com.company.Items.Item;
import com.company.Map.LevelData;
import com.company.Map.LevelFactory;
import com.company.Map.Room;

import java.awt.*;
import java.util.Collection;

public class Game {

    private PlayerEntity playerData;
    private LevelData currentLevelData;
    private CommandGroup gameCommands;

    private int level = 0;
    public void startNewGame(){
        initialisePlayer();
        initialiseCommands();
        goToNextLevel();
        runGame();
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
        gameCommands.addCommand("inventory",(param) -> playerData.displayInventory());
        gameCommands.addCommand("use",playerData::useItem);
        gameCommands.addCommand("equip",this::equip);
        gameCommands.addCommand("help",(param) -> help());
        gameCommands.addCommand("quit",(param) -> quit());
    }
    private void displayInfo(String arguments){
        playerData.printData();
    }
    private void moveRoom(String direction){
        Point movePoint = currentLevelData.getPointInDirection(direction);
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
        playerData.heal(5);
        if(currentLevelData.getCurrentRoom().hasMonster()){
            //Start battle
            Battle battle = currentLevelData.getCurrentRoom().getRoomBattle();
            int battleStatus = battle.startBattle(playerData);
            if(battleStatus == 1){
                //Player has died
                System.out.println("You are dead. Game over.");
                quit();
            }
            else if(battleStatus == 2){
                //Monsters defeated
                currentLevelData.defeatedMonster(playerData,false);
            }
            else if(battleStatus == 3){ //Floor boss defeated
                //Win level
                //Go to next level
                System.out.println("You have killed the boss of this level.");
                currentLevelData.defeatedMonster(playerData,true);
                System.out.println("Moving to the next floor");
                goToNextLevel();
            }
        }
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


    private void equip(String item){
        Item itemToEquip = CommandParser.parseItemCommand(item,playerData.getItemInventory());
        if(itemToEquip != null){
            if(itemToEquip.getClass().isInstance(EquippableItem.class)){
                playerData.equipItem((EquippableItem)itemToEquip);
            }
            else{
                System.out.println("You cant equip that.");
            }
        }
        else{
            System.out.println("You dont have an item called " + item);
        }
    }

    private void help(){
        gameCommands.printAllCommands();
    }

    private void quit(){
        GameLauncher.exit();
    }

    private void goToNextLevel(){
        level++;
        currentLevelData = LevelFactory.getNextLevel();
        if(currentLevelData == null){
            //No more levels
            //Win the game
            System.out.println("Congrats you have won the game");
            quit();
        }
    }

}
