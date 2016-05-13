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
@WebService(serviceName = "SpyService")
public class SpyService {
    
    private SpyList spyList = SpyList.getInstance();
//    Collection list = spyList.getList();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addSpy")
    public String addSpy(@WebParam(name = "name") String name, @WebParam(name = "password") String password, @WebParam(name = "title") String title, @WebParam(name = "location") String location) {
        Spy spy = new Spy(name, password, title, location);
        String result = "";
        if(spyList.get(name) == null) {
            spyList.add(spy);
            result = spy.toString();
        } else {
            result = "Spy already exists, no update was made.";
        }
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateSpy")
    public String updateSpy(@WebParam(name = "name") String name, @WebParam(name = "password") String password, @WebParam(name = "title") String title, @WebParam(name = "location") String location) {
        Spy spy = spyList.get(name);
        String result = "";
        if(spy == null) {
            result = "Spy no exists, no update was made.";
        } else {
            spy.setName(name);
            spy.setPassword(password);
            spy.setLocation(location);
            spy.setTitle(title);
            result = spy.toString();
        }
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getSpy")
    public String getSpy(@WebParam(name = "name") String name) {
        Spy spy = spyList.get(name);
        String result = "";
        if(spy == null) {
            result = "No such spy.";
        } else {
            result = spy.toString();
        }
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteSpy")
    public String deleteSpy(@WebParam(name = "name") String name) {
        Spy spy = spyList.get(name);
        String result = "";
        if(spy == null) {
            result = "No such spy.";
        } else {
            result = "Spy" + spy.getName() + "was deleted from the list.";
            spyList.delete(spy);
        }
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getList")
    public String getList() {
        return spyList.toString();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getListAsXML")
    public String getListAsXML() {
        return spyList.toXML();
    }

    
}
