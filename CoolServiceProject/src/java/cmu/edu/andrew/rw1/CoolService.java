/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmu.edu.andrew.rw1;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Rui
 */
@WebService(serviceName = "CoolService")
public class CoolService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "purchase")
    public String purchase(@WebParam(name = "purchaseID") int purchaseID, @WebParam(name = "name") String name, @WebParam(name = "buy") int buy) {
        System.out.println("Purchase");
        return "You are purchesed" + name;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "foo")
    public double foo(@WebParam(name = "x") double x) {
        //TODO write your implementation code here:
        return x* 2;
    }

}
