/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CsuEastBay.bean;


import CsuEastBay.model.Address;
import CsuEastBay.Util.Item;
import CsuEastBay.Util.ItemList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author welcome
 */
public class UserAccount implements Serializable{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userName;
    private Address address;
    private String phone;
    private Hashtable<String, ItemList> item = new Hashtable<String, ItemList>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Hashtable<String, ItemList> getItem() {
        return item;
    }

    public void setItem(Hashtable<String, ItemList> item) {
        this.item = item;
    }
    
    public void addItem(String email, ItemList items)
    {
        item.put(email, items);
    }

    public UserAccount(String firstName, String lastName, String email, String password, String userName, Address address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
    }

    public UserAccount() {
    }

    public void setItem(String email, Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
