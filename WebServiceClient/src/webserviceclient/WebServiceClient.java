/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceclient;

import java.math.BigInteger;

/**
 *
 * @author Rui
 */
public class WebServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigInteger ctr = new BigInteger("0");
        BigInteger t10000 = new BigInteger("10000");
        
        long start = System.currentTimeMillis();
        while(!ctr.equals(t10000)){
            bump();
            ctr = get();
        }
        long stop = System.currentTimeMillis();
        
        System.out.println("The time cost: " + (stop - start));

    }

    private static boolean bump() {
        cmu.edu.andrew.rw1.Bump_Service service = new cmu.edu.andrew.rw1.Bump_Service();
        cmu.edu.andrew.rw1.Bump port = service.getBumpPort();
        return port.bump();
    }

    private static BigInteger get() {
        cmu.edu.andrew.rw1.Bump_Service service = new cmu.edu.andrew.rw1.Bump_Service();
        cmu.edu.andrew.rw1.Bump port = service.getBumpPort();
        return port.get();
    }
    
}
