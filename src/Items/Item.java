package Items;

import java.util.Set;

public abstract class Item {

    private Set<String> acceptedStrings;

    /**
     * Set an accepted name for this item that will be recognised by the parser
     * @param name The string to accept (will be set to lowercase automagically)
     */
    protected void setAcceptedName(String name){
        acceptedStrings.add(name.toLowerCase());
    }

    public Set<String> getAcceptedNames(){
        return acceptedStrings;
    }

}
