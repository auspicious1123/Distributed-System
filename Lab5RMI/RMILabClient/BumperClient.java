//*******************************************************
// CalculatorClient.java
// This client gets a remote reference from the rmiregistry
// that is listening on the default port of 1099.
// It allows the client to quit with a "!".
// Otherwise, it computes the sum of two integers
// using the remote calculator.

import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class BumperClient {
    public static void main(String args[]) throws Exception {
        Bumper b  = (Bumper) Naming.lookup("//localhost/bumper");
        System.out.println("Found bumper");

        BigInteger ctr = new BigInteger("0");
        BigInteger t10000 = new BigInteger("10000");

        long start = System.currentTimeMillis();
        while(!ctr.equals(t10000)){
            b.bump();
            ctr = b.get();
        }
        long stop = System.currentTimeMillis();

        System.out.println("The time cost: " + (stop - start));
    }
}
