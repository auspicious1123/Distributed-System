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
 * Second client for update stock price.
 * @author Rui
 */
public class StockRMIClientPriceUpdate {
    
    public static void main(String[] arg) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter stock symbol and price or ! to quit.");
        
        while (true) {
            try {
                String line  = br.readLine();
                // if a "!" is entered just exit
                if(line.equals("!")) 
                    System.exit(0);
                // read in stock name and update price.
                if(!line.equals("")) {
                    // get stock name and price.
                    String[] inputLine = line.split(" ");
                    Registry registry = LocateRegistry.getRegistry(1099);
                    StockRMI remoteService = (StockRMI) registry.lookup("StockRMI");
                    remoteService.stockUpdate(inputLine[0], 
                            Double.parseDouble(inputLine[1])); // call update method.
                }
                
            } catch (IOException | NotBoundException ex) {
                Logger.getLogger(StockRMIClientPriceUpdate.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }
}
