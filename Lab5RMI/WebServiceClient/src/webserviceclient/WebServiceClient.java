/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceclient;

import java.math.BigInteger;

/**
 *
 * @author Kid7st
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
        edu.cmu.andrew.yshi1.Bump_Service service = new edu.cmu.andrew.yshi1.Bump_Service();
        edu.cmu.andrew.yshi1.Bump port = service.getBumpPort();
        return port.bump();
    }

    private static BigInteger get() {
        edu.cmu.andrew.yshi1.Bump_Service service = new edu.cmu.andrew.yshi1.Bump_Service();
        edu.cmu.andrew.yshi1.Bump port = service.getBumpPort();
        return port.get();
    }
    
}
