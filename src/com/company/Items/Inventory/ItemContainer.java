package com.company.Items.Inventory;

import com.company.Items.Item;

import java.util.List;

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
        List<Item> inven = getItemInventory();
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
    List<Item> getItemInventory();

    default void displayInventory(){
        List<Item> inven = getItemInventory();
        System.out.println("____________________________________");
        System.out.println("Item Inventory: ");
        for(int i = 0;i < inven.size(); i++){
            System.out.println(i+1 + ". " + inven.get(i).toString());
        }
        System.out.println("____________________________________");
    }
}
