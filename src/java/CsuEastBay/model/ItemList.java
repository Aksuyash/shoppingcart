/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CsuEastBay.Util;

import java.util.ArrayList;

/**
 *
 * @author welcome
 */
public class ItemList {
    private static ArrayList<Item> itemList = new ArrayList();

    public ItemList() {
    }

    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    public static void setItemList(ArrayList<Item> itemList) {
        ItemList.itemList = itemList;
    }
    
    public void  addItem(Item item)
    {
        itemList.add(item);
    }
    
    public void deleteItem(Item item)
    {
        itemList.remove(item);
    }
}
