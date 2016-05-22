/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CsuEastBay.Util;

import CsuEastBay.model.Address;
import CsuEastBay.bean.UserAccount;
import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author welcome
 */
public class DataBase implements Serializable{
    
    private static Connection getConnection()
    {
        Connection connection = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/cs3520";
            String username = "root";
            String password = "admin";
            connection = DriverManager.getConnection(
            dbURL, username, password);
        } catch(SQLException e) {
            for (Throwable t : e)
                 t.printStackTrace();
        }
        return connection;
    }
    
    
    public ArrayList<UserAccount> getUser() throws SQLException
    {
        ArrayList<UserAccount> userAccount = null;
        try{
            Connection connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
            "SELECT * FROM UserTable, ItemTable, AddressTable WHERE UserTable.Email = ItemTable.Email"
                    + "AND ItemTable.Email = AddressTable.Email");
            Item item = null;
            UserAccount user = null;
            Address address = null;
            ItemList itemArray =  null;
            while (rs.next())
            {
                user.setFirstName(rs.getString("fristName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserName(rs.getString("userName"));
                user.setPhone(rs.getString("phone"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setDate(rs.getString("date"));
                item.setImgText((ArrayList<String>) rs.getArray("img"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setStreetName(rs.getString("StreetName"));
                address.setZipCode(rs.getString("zipCode"));
                itemArray.addItem(item);
                user.addItem(user.getEmail(), itemArray);
                user.setAddress(address);
                userAccount.add(user);
            }
            rs.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            for (Throwable t : e)
                 t.printStackTrace();
        }
        return userAccount;
    }
    
    
    public boolean InsertUser(ArrayList<UserAccount> user) throws SQLException
    {
        boolean check = false;
        try{
            Connection connection = DataBase.getConnection();
            PreparedStatement userPS = null;
            PreparedStatement addressPS = null;
            PreparedStatement itemPS = null;

            String userTableQuery = (
            "INSERT INTO UserTable (firstName, lastName, email, password, userName, phone)" + "VALUE(?, ? , ?, ?, ?, ?)");
            String itemTableQuery = ("INSERT INTO ItemTable(name, price, date, img)" + "VALUE(?, ?, ?, ?)");
            String addressTableQuery = ("INSERT INTO AddressTable(city, state, streetName, zipCode)" + "VALUE(?, ?, ?, ?)");
            userPS = connection.prepareStatement(userTableQuery);
            itemPS = connection.prepareStatement(itemTableQuery);
            addressPS = connection.prepareStatement(addressTableQuery);

            int i;
            int j;
            for (i = 0; i < user.size(); i++)
            {
                userPS.setString(1, user.get(i).getFirstName());
                userPS.setString(2, user.get(i).getLastName());
                userPS.setString(3, user.get(i).getEmail());
                userPS.setString(4, user.get(i).getPhone());
                userPS.setString(5, user.get(i).getUserName());
                userPS.setString(6, user.get(i).getPhone());
                Hashtable<String, ItemList> item = user.get(i).getItem();
                for (String key: item.keySet()){
                    ItemList itemArray = item.get(key);
                    ArrayList<Item> itemNumber = itemArray.getItemList();
                    for (j = 0; j < itemNumber.size(); j++){
                        itemPS.setString(1, itemNumber.get(j).getName());
                        itemPS.setDouble(2, itemNumber.get(j).getPrice());
                        itemPS.setString(3, itemNumber.get(j).getDate());
                        itemPS.setArray(4, (Array) itemNumber.get(j).getImgText());
                    }
                }
                Address address = user.get(i).getAddress();
                addressPS.setString(1, address.getCity());
                addressPS.setString(2, address.getState());
                addressPS.setString(3, address.getStreetName());
                addressPS.setString(4, address.getZipCode());
                addressPS.executeUpdate();
                userPS.executeUpdate();
                itemPS.executeUpdate();

            }   
            userPS.close();
            itemPS.close();
            addressPS.close();
            connection.close();
            check = true;
        }
        catch(SQLException e) {
            for (Throwable t : e)
                 t.printStackTrace();
        }
        return check;
    }
    
    
    public boolean DeleteUser(UserAccount user) throws SQLException
    {
        boolean check = false;
        try{
            Connection connection = DataBase.getConnection();
            PreparedStatement userPS = null;
            PreparedStatement addressPS = null;
            PreparedStatement itemPS = null; 
            String userTableQuery = ("DELETE FROM UserTable WHERE Email = ?");
            String itemTableQuery = ("DELETE FROM itemTable WHERE Email = ?");
            String addressTableQuery = ("DELETE FROM addressTable WHERE Email = ?");
            userPS = connection.prepareStatement(userTableQuery);
            itemPS = connection.prepareStatement(itemTableQuery);
            addressPS = connection.prepareStatement(addressTableQuery);
            userPS.setString(1, user.getEmail());
            addressPS.setString(1, user.getEmail());
            itemPS.setString(1, user.getEmail());
            userPS.executeUpdate();
            addressPS.executeUpdate();
            itemPS.executeUpdate();
            userPS.close();
            itemPS.close();
            addressPS.close();           
            connection.close();
            check = true;
        }
        catch(SQLException e) {
            for (Throwable t : e)
                 t.printStackTrace();
        }
        return check;
    }       
    
    
    public boolean UpdateUser(UserAccount user) throws SQLException
    {
        boolean check = false;
        try{
            Connection connection = DataBase.getConnection();
            PreparedStatement userPS = null;
            PreparedStatement addressPS = null;
            PreparedStatement itemPS = null;
            String userTableQuery = (
            "UPDATE UserTable (firstName, lastName, email, password, userName, phone)" + "VALUE(?, ? , ?, ?, ?, ?)");
            String itemTableQuery = ("UPDATE ItemTable(name, price, date, img)" + "VALUE(?, ?, ?, ?)");
            String addressTableQuery = ("UPDATE AddressTable(city, state, streetName, zipCode)" + "VALUE(?, ?, ?, ?)");
            userPS = connection.prepareStatement(userTableQuery);
            itemPS = connection.prepareStatement(itemTableQuery);
            addressPS = connection.prepareStatement(addressTableQuery);
            userPS.setString(1, user.getFirstName());
            userPS.setString(2, user.getLastName());
            userPS.setString(3, user.getEmail());
            userPS.setString(4, user.getPhone());
            userPS.setString(5, user.getUserName());
            userPS.setString(6, user.getPhone());
            Hashtable<String, ItemList> item = user.getItem();
            int j;
            for (String key: item.keySet()){
                    ItemList itemArray = item.get(key);
                    ArrayList<Item> itemNumber = itemArray.getItemList();
                    for (j = 0; j < itemNumber.size(); j++){
                        itemPS.setString(1, itemNumber.get(j).getName());
                        itemPS.setDouble(2, itemNumber.get(j).getPrice());
                        itemPS.setString(3, itemNumber.get(j).getDate());
                        itemPS.setArray(4, (Array) itemNumber.get(j).getImgText());
                    }
                }
            Address address = user.getAddress();
            addressPS.setString(1, address.getCity());
            addressPS.setString(2, address.getState());
            addressPS.setString(3, address.getStreetName());
            addressPS.setString(4, address.getZipCode());
            addressPS.executeUpdate();
            userPS.executeUpdate();
            itemPS.executeUpdate();            
            addressPS.close();
            itemPS.close();
            userPS.close();
            connection.close();
            check = true;
        }
        catch(SQLException e) {
            for (Throwable t : e)
                 t.printStackTrace();
        }
        return check;
    }       
}