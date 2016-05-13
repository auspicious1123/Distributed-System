/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockrmiserverproject;

import sameinterface.StockRMI;
import sameinterface.Notifiable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implement class for server.
 * @author Rui
 */
public class StockRMIServant extends UnicastRemoteObject implements StockRMI {
    
    /* Given a stock, get a list of users that are interested in that stock. */
    private static Map<String, ArrayList<String>> stocks = new TreeMap<>();
    
    /* Given a user, get the remote object reference to its callback method. */ 
    private static Map<String, Notifiable> users = new TreeMap<>();
    
    /**
     * Constructor.
     * @throws RemoteException 
     */
    public StockRMIServant() throws RemoteException { }
    
    /**
     * subscribe a stock.
     * @param user
     * @param stockSym
     * @return
     * @throws RemoteException 
     */
    @Override
    public boolean subscribe(String user, String stockSym) throws RemoteException {
        System.out.println("User " + user + " subscribe stock " + stockSym);
        // if stocks have this stock, add this user, otherwise new an object add into 
        // the stocks map.
        if (stocks.containsKey(stockSym)) {
            ArrayList<String> userList = stocks.get(stockSym); // save user list.
            userList.add(user);
        } else {
            ArrayList<String> userList = new ArrayList<>();
            userList.add(user);
            stocks.put(stockSym, userList);
        }
        return true;
    }

    /**
     * unsubscribe a stock.
     * @param user
     * @param stockSym
     * @return
     * @throws RemoteException 
     */
    @Override
    public boolean unSubscribe(String user, String stockSym) throws RemoteException {
        // if user is in stockSym, unsubscribe. And delete if from userList.
        if (stocks.containsKey(stockSym)) {
            System.out.println("User " + user + " unsubscribe stock " + stockSym);
            ArrayList<String> userList = stocks.get(stockSym);
            if (userList.size() > 1) {
                userList.remove(user);  // delete for userList.
                System.out.println(user + "is removed");
            } else {
                // if no user in the userList of stockSym, delete stockSym.
                stocks.remove(stockSym);
                System.out.println("Stock is empty " + stockSym + " is removed");
            }
        } else {
            // exception this user do not subscribe this stock.
            System.out.println( user + " does not subscribe : " + stockSym);
        }
        return true;
    }

    /**
     * Update a stock.
     * @param stockSym
     * @param price
     * @throws RemoteException 
     */
    @Override
    public void stockUpdate(String stockSym, double price) throws RemoteException {
        System.out.println("Update stock " + stockSym + " price as :" + price);
        ArrayList<String> userList = stocks.get(stockSym);
        // check whether exsits user register the stock
        if (userList == null || userList.isEmpty()) {
            System.out.println("No user refister this stock");
        } else {
            // notify all the users registry the stock.
            for (String user : userList) {
                if (users.containsKey(user)) {
                    users.get(user).notify(stockSym, price);
                } 
            }
        } 
    }
    /**
     * Registry callback.
     * @param remoteClient
     * @param user 
     */
     @Override
    public void registerCallBack(Notifiable remoteClient, String user) {
        // put userName into remote service
        if (!users.containsKey(user)) {
            users.put(user, remoteClient);
            System.out.println(user + " has register callback!");
        }
    }

    /**
     * deRegisterCallBack.
     * @param user
     * @throws RemoteException 
     */
    @Override
    public void deRegisterCallBack(String user) throws RemoteException {
        if (users.containsKey(user)) {
            users.get(user).exit();   // close user notify.
            users.remove(user);
            System.out.println(user + " is deregistered call back");
        } else {
            System.out.println(user + " exist");
        }
    }
    
    /**
     * It is called by the server when it is time to terminate the thread that is
     * handing callbacks on the client.
     * @throws RemoteException 
     */
    public void exit() throws RemoteException{
        try{
            UnicastRemoteObject.unexportObject(this, true); 
            System.out.println("StockPriceCallBack exiting.");
        }
        catch(Exception e){ 
            System.out.println("Exception thrown" + e); 
        } 
    }
}
