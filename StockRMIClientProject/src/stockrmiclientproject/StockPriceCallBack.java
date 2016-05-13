/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockrmiclientproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import sameinterface.Notifiable;
import sameinterface.StockRMI;

/**
 * First client for building callback.
 * @author Rui
 */
public class StockPriceCallBack extends UnicastRemoteObject implements Notifiable {

    public StockPriceCallBack() throws  RemoteException { }
    /**
     * Get remote regisrtry service and build callback.
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        System.out.println("Enter user name:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userName = br.readLine();  // read user name.
        
        System.out.println("Looking up the server in the registry.");
        Registry registry = LocateRegistry.getRegistry(1099);
        StockRMI remoteService = (StockRMI) registry.lookup("StockRMI");
        
        System.out.println("Creating a callback object to handle calls from the server.");
        StockPriceCallBack stockPriceCallBack = new StockPriceCallBack();
        
        System.out.println("Registering the callback with a name at the server.");
        remoteService.registerCallBack(stockPriceCallBack, userName);  // call registry method.
        System.out.println("Callback handler for " + userName + " ready");
    }

    /**
     * notify to client stock update information.
     * @param stockSym
     * @param price
     * @throws RemoteException 
     */
    @Override
    public void notify(String stockSym, double price) throws RemoteException {
         System.out.println("Update stock notify: " + stockSym +" "+ price);
    }
    

    /**
     * Exit and close all callback notify.
     * @throws RemoteException 
     */
    @Override
    public void exit() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
        System.out.println("This client close all no notify and "
                + "no logner receives call abcks from the server");
    }
    
}
