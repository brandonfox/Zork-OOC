package com.company.Combat;

import com.company.Commands.CommandGroup;
import com.company.Commands.CommandParser;
import com.company.Entities.Abstract.Creature;
import com.company.Entities.Abstract.Monster;
import com.company.Entities.PlayerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Battle {

    private PlayerEntity player;
    private List<Monster> monsters;

    private CommandGroup battleCommands;

    public Battle(Monster... fightEntities){
        monsters = new ArrayList<>();
        monsters.addAll(Arrays.asList(fightEntities));
    }

    /**
     * Start a battle
     * @return 1 if the player lost, 2 if the monsters were defeated
     */
    public int startBattle(PlayerEntity player){
        this.player = player;
        initialiseBattleCommands();
        printPreBattleMessage();
        while(true){
            printBattleInfo();
            CommandParser.getAndExecuteCommand(battleCommands);
            monsters = getLivingMonsters();
            doMonsterStep();
            int battleStatus = getBattleStatus();
            if(battleStatus != 0){
                return battleStatus;
            }
        }
    }

    private void doMonsterStep(){
        for (Monster c: monsters) {
            doAttack(c,player);
        }
    }

    /**
     * Get the status of the battle
     * @return 0 if the battle is still ongoing, 1 if the player is dead, 2 if the monsters are defeated
     */
    private int getBattleStatus(){
        if(player.getCurrentHealth() <= 0){
            doDeadPlayerStuff();
            return 1;
        }
        else if(getLivingMonsters().size() == 0){
            winBattle();
            return 2;
        }
        else {
            return 0;
        }
    }
    private void winBattle(){
        displayWinMessage();
    }

    private void displayWinMessage(){
        System.out.println("Congrats you have won the battle");
        System.out.println("----------------------------------------------------");
    }
    private void doDeadPlayerStuff(){
        System.out.println("Player has died");
    }

    private List<Monster> getLivingMonsters(){
        List<Monster> updatedEntities = new ArrayList<>(monsters);
        for (Monster e: monsters) {
            if(e.getCurrentHealth() <= 0){
                player.increaseExperience(e.getExperienceValue());
                updatedEntities.remove(e);
            }
        }
        return updatedEntities;
    }


    private void attack(String attackMonster){
        if(monsters.size() == 1){
            doAttack(player,monsters.get(0));
        }
        else{
            //TODO add code for multiple monsters
            //Parse monster names or get a number
        }
    }

    private static final Random random = new Random();

    private void doAttack(Creature attacker, Creature defender){
        System.out.println(attacker + " attacks the " + defender);
        int hitChance = attacker.getHitAccuracy() - defender.getHitAvoidance();
        boolean hit = random.nextInt(100) <= hitChance;
        if(hit){
            int defence = defender.getDefence();
            int attack = attacker.getAttack() - defence;
            defender.takeDamage(attack);
            System.out.println(defender + " takes " + attack + " damage");
        }
        else{
            System.out.println("The attack misses");
        }
    }

    private void initialiseBattleCommands(){
        battleCommands = new CommandGroup();
        battleCommands.addCommand("attack",this::attack);
        battleCommands.addCommand("use",player::useItem);
        battleCommands.addCommand("inventory",(param) -> player.displayInventory());
    }

    private void printPreBattleMessage(){
        StringBuilder battleMessage = new StringBuilder();
        battleMessage.append("You are fighting");
        if(monsters.size() == 1)
            battleMessage.append(" a");
        battleMessage.append(":");
        System.out.println(battleMessage.toString());
        for (Creature e: monsters) {
            System.out.println(e.toString());
        }
    }
    private void printBattleInfo(){
        printCreatureHealth(player);
        for (Creature c: monsters) {
            printCreatureHealth(c);
        }
        battleCommands.printCommandsInline();
    }
    private void printCreatureHealth(Creature c){
        c.printHealth();
    }

}
