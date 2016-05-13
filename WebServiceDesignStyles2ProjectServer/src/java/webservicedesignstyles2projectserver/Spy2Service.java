/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles2projectserver;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Rui
 */
@WebService(serviceName = "Spy2Service")
public class Spy2Service {

    private SpyList spyList = SpyList.getInstance();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "spyOperation")
    public String spyOperation(@WebParam(name = "inputString") String inputString) {
        String result = "";
        SpyMessage spyMessage = new SpyMessage();
        Document spyDoc = spyMessage.getDocument(inputString);
        spyDoc.getDocumentElement().normalize();
        System.out.println("Root element :" + spyDoc.getDocumentElement().getNodeName());
        // the root element should be “spyMessage”
        
        //get operation name
        NodeList operation;
        operation = spyDoc.getElementsByTagName("operation");
        Node operationList = operation.item(0);
        String operationName = operationList.getTextContent(); 
        
        // get name;
        NodeList getName; 
        getName = spyDoc.getElementsByTagName("name");
        Node nameList = getName.item(0);
        String name = nameList.getTextContent(); 
        
        // get spy title;
        NodeList getSpyTitle; 
        getSpyTitle = spyDoc.getElementsByTagName("spyTitle");
        Node spyTitleList = getSpyTitle.item(0);
        String title = spyTitleList.getTextContent();
        
        // get spy location;
        NodeList getLocation; 
        getLocation = spyDoc.getElementsByTagName("spyTitle");
        Node locationList = getLocation.item(0);
        String location = locationList.getTextContent();
        
        // get spy password;
        NodeList getPassword; 
        getPassword = spyDoc.getElementsByTagName("spyTitle");
        Node passwordList = getPassword.item(0);
        String password = passwordList.getTextContent();
        
        Spy spy;
        switch(operationName) {
            case "addSpy":
                spy = new Spy(name, password, title, location);
                if(spyList.get(name) == null) {
                    spyList.add(spy);
                    result = spy.toString();
                } else {
                    result = "Spy already exists, no update was made.";
                }
               break;
                
            case "updateSpy":
                spy = spyList.get(name);
                if(spy == null) {
                result = "Spy no exists, no update was made.";
                } else {
                    spy.setName(name);
                    spy.setPassword(password);
                    spy.setLocation(location);
                    spy.setTitle(title);
                    result = spy.toString();
                }
                break;
                
            case "getSpy":
                spy = spyList.get(name);
                if(spy == null) {
                    result = "No such spy.";
                } else {
                    result = spy.toString();
                }
                break;
                
            case "deleteSpy":
                spy = spyList.get(name);
                if(spy == null) {
                result = "No such spy.";
                } else {
                    result = "Spy" + spy.getName() + "was deleted from the list.";
                    spyList.delete(spy);
                }
                break;
        
            case "getList":
                result = spyList.toString();
                break;
                
            case "getListAsXML":
                result = spyList.toXML();
                break;
            default:
                break;
        }
        return result;
    }
        
}
