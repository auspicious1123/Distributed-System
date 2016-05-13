/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles2projectclient;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Rui
 */
public class SpyMessage {
    
    String name;
    String title;
    String location;
    String password;
    String operation;
    
    public SpyMessage(Spy spy, String operation) {
        
        this.name = spy.getName();
        this.title = spy.getTitle();
        this.location = spy.getLocation();
        this.password = spy.getPassword();
        this.operation = operation;
    }
    
    public Document getDocument(String xmlString) { 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); DocumentBuilder builder;
        Document spyDoc = null;
    try {
        builder = factory.newDocumentBuilder();
        spyDoc = (Document) builder.parse( new InputSource( new StringReader( xmlString ) ) );
        } catch (Exception e) { 
            e.printStackTrace();
        }
    return spyDoc; 
    }
    
    public String toXML() {
        StringBuffer xml = new StringBuffer();
        xml.append("<spyMessage>");
        xml.append("<spy>");
        xml.append("<operation>" + operation + "</operation>");
        xml.append("<name>" + name + "</name>"); 
        xml.append("<spyTitle>" + title + "</spyTitle>"); 
        xml.append("<location>" + location + "</location>"); 
        xml.append("<password>" + password + "</password>"); 
        xml.append("</spy>");
        xml.append("</spyMessage>");
        return xml.toString();
    }
}
