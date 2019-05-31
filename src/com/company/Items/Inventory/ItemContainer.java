package com.company.Items.Inventory;

import com.company.Items.Item;

import java.util.Collection;

public interface ItemContainer {

    default boolean collectItem(Item item){
        if(item != null){
            getItemInventory().add(item);
            return true;
        }
        else
            return false;
    }

    /**
     * Remove item from inventory
     * @param item the item to remove
     * @return boolean representing if inventory contains item and can remove it
     */
    default boolean removeItem(Item item){
        if(item == null)
            return false;
        Collection<Item> inven = getItemInventory();
        if(inven.contains(item)){
            inven.remove(item);
            return true;
        }
        else
            return false;
    }
    default boolean transferItemTo(Item item, ItemContainer container){
        return removeItem(item) && container.collectItem(item);
    }
    Collection<Item> getItemInventory();

}
