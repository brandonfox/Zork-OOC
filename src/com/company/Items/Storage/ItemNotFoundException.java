package com.company.Items.Storage;

import com.company.Items.Item;

public class ItemNotFoundException extends Exception {

    private Object container;
    private Item item;

    public ItemNotFoundException(Object obj, Item item){
        container = obj;
        this.item = item;
    }

    @Override
    public String toString() {
        return "Container: " + container.toString() + " does not contain Item: " + item.toString();
    }
}
