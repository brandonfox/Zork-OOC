package com.company.Items;

import com.company.CloneableObject;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Item implements CloneableObject {

    private Set<String> acceptedStrings = new HashSet<>();
    protected static Random random = new Random();
    private int itemlevel;
    /**
     * Set an accepted name for this item that will be recognised by the parser
     * @param name The string to accept (will be set to lowercase automagically)
     */
    protected void setAcceptedName(String name){
        acceptedStrings.add(name.toLowerCase());
    }

    protected void setItemLevel(int level){itemlevel = level;}
    public int getItemLevel(){return itemlevel;}
    public Set<String> getAcceptedNames(){
        return acceptedStrings;
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        }catch(CloneNotSupportedException e){
            System.out.println("Error while cloning " + this.toString());
            return null;
        }
    }
}
