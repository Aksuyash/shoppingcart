/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CsuEastBay.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author welcome
 */
public class Cart implements Serializable{
    private ArrayList<LineItem> items = new ArrayList();

    public Cart() {
    }
    
    public void addItem(LineItem item){
        String code = item.getProduct().getName();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++){
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getName().equals(code)) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }

    public Cart(ArrayList<LineItem> items) {
        this.items = items;
    }

    public List<LineItem> getItem() {
        return items;
    }

    public void setItem(ArrayList<LineItem> items) {
        this.items = items;
    }
    public int getCount() {
        return items.size();
    }

    public void removeItem(LineItem item) {
        String code = item.getProduct().getName();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getName().equals(code)) {
                items.remove(i);
                return;
            }
        }
        items.remove(item);
    }    
}
