/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmu.edu.andrew.rw1;

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.math.BigInteger;

/**
 *
 * @author Rui
 */
@WebService(serviceName = "Bump")
public class Bump {
    private BigInteger source;
    private BigInteger one;
    
    public Bump() {
        source = new BigInteger("0");
        one = new BigInteger("1");
    }
    
    
    @WebMethod(operationName = "bump")
    public boolean bump() {
        source = source.add(one);
        return true;
    }
    
    @WebMethod(operationName = "get")
    public BigInteger get() {
        return source;
    }
}
