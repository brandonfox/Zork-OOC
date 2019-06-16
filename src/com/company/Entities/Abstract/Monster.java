package com.company.Entities.Abstract;


import com.company.Items.Item;
import com.company.Items.Potion;
import com.company.ProbabilityMap;

public abstract class Monster extends Creature {

    public Monster(String creatureName, int level,int baseHealth,int baseDamage,int randomAttackDeviation,int baseAccuracy, int armour,int strength, int dexterity, int vitality, int baseExp){
        super(creatureName,level,baseHealth,baseDamage,randomAttackDeviation,baseAccuracy,armour,strength,dexterity,vitality);
        this.experienceValue = baseExp + Math.round(baseExp * level * 0.3f) + random.nextInt(baseExp/3);
        monsterItemDrops = new ProbabilityMap<>();

        //Add default item drops shared by all monsters
        //Potion will accept all types of potion
        monsterItemDrops.setKey(new Potion(level),4);
    }

    /**
     * The chance the monster drops an item on defeat
     *  Overwrite this method to change drop chance
     * @return A percentage
     */
    private int getMonsterItemDropChance(){
        return 75;
    }
    private ProbabilityMap<Item> monsterItemDrops;

    //Method for monster specific viable drops
    protected void addExtraDrops(){

    }
    protected void addItemDrop(Item item,int dropWeight){
        monsterItemDrops.setKey(item,dropWeight);
    }

    public Item getRandomDrop(boolean guaranteedDrop){
        if(guaranteedDrop || random.nextInt(100) <= getMonsterItemDropChance()){
            return monsterItemDrops.getRandomItem();
        }
        else
            return null;
    }

    private int experienceValue;
    public int getExperienceValue(){return experienceValue;}

}
