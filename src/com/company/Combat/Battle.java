package com.company.Combat;

import com.company.Commands.CommandGroup;
import com.company.Commands.CommandParser;
import com.company.Entities.Creature;
import com.company.Entities.PlayerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Battle {

    private PlayerEntity player;
    private List<Creature> monsters;

    private CommandGroup battleCommands;

    public Battle(PlayerEntity player, Creature... fightEntities){
        monsters = new ArrayList<>();
        this.player = player;
        monsters.addAll(Arrays.asList(fightEntities));
        initialiseBattleCommands();
    }

    public void doBattle(){
        printPreBattleMessage();
        boolean hasEnded = false;
        while(!hasEnded){
            printBattleInfo();
            CommandParser.getAndExecuteCommand(battleCommands);
            monsters = getLivingMonsters();
            doMonsterStep();
            hasEnded = !getBattleStatus();
        }
    }

    private void doMonsterStep(){
        for (Creature c: monsters) {
            doAttack(c,player);
        }
    }

    /**
     * Get the status of the battle
     * @return true if the battle is currently ongoing, false if the battle has ended
     */
    private boolean getBattleStatus(){
        if(player.getCurrentHealth() <= 0){
            doDeadPlayerStuff();
        }
        else if(getLivingMonsters().size() == 0){
            winBattle();
        }
        else{
            return true;
        }
        return false;
    }
    private void winBattle(){
        displayWinMessage();
    }
    private void displayWinMessage(){
        System.out.println("Congrats you have won the battle");
    }
    private void doDeadPlayerStuff(){
        System.out.println("Player has died");
    }

    private List<Creature> getLivingMonsters(){
        List<Creature> updatedEntities = new ArrayList<>(monsters);
        for (Creature e: monsters) {
            if(e.getCurrentHealth() <= 0){
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
        battleCommands.printCommandsInline();
    }

}
