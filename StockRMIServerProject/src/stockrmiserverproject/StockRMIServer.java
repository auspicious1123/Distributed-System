/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockrmiserverproject;

import sameinterface.StockRMI;
import java.rmi.registry.*;

/**
 * Serve./
 * @author Rui
 */
public class StockRMIServer {
    public static void main(String args[]) {
        
        try {
            // Build stockRMI.
            StockRMI stockService = new StockRMIServant();
            Registry registry = LocateRegistry.createRegistry(1099);
            // bind remote registry with "StockRMI".
            registry.rebind("StockRMI", stockService);
            System.out.println("Server is running");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
