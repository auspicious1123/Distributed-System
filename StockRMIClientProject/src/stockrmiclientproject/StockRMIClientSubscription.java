/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockrmiclientproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import sameinterface.StockRMI;

/**
 * Third client for subscript stock.
 * @author Rui
 */
public class StockRMIClientSubscription {
    /**
     * Subscribe or unsubscribe a stock.
     * @param args 
     */
    public static void main(String[] args) {
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter user name: ");
            String userName = br.readLine();
            System.out.println("Enter S for subscribe or U for unsubscribe "
                    + "followed by the stock symbol of interest. Enter ! to quit. ");
            while (true) {
                String line = br.readLine();
                Registry registry = LocateRegistry.getRegistry(1099);
                StockRMI remoteService = (StockRMI) registry.lookup("StockRMI");
                // when input !, close this client all callback notification.
                if(line.equals("!")) {
                    System.out.println("Client exist!");
                    remoteService.deRegisterCallBack(userName);
                    
                }
                if(!line.equals("")) {
                    String[] input = line.split(" ");
                    // check input to choose subscribe or unsubscribe.
                    if (input[0].equals("S")) {
                        remoteService.subscribe(userName, input[1]);
                        // once subscribe a stock, notify it.
                        StockPriceCallBack callback = new StockPriceCallBack();
                        remoteService.registerCallBack(callback, userName);
                    }
                    if (input[0].equals("U")) {
                        remoteService.unSubscribe(userName, input[1]);
                    }
                }
            }
        } catch (IOException | NotBoundException ex) {
            Logger.getLogger(StockRMIClientSubscription.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
