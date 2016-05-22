/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CsuEastBay.bean;

import CsuEastBay.Util.Item;
import java.io.Serializable;
import java.text.NumberFormat;

/**
 *
 * @author welcome
 */
public class LineItem implements Serializable {

    private Item item;
    private int quantity;

    public LineItem() {}

    public void setProduct(Item p) {
        item = p;
    }

    public Item getProduct() {
        return item;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        double total = item.getPrice() * quantity;
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }
}


