package com.company.Items;

import java.util.Random;
import java.util.Set;

public abstract class Item {

    private Set<String> acceptedStrings;
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

}
