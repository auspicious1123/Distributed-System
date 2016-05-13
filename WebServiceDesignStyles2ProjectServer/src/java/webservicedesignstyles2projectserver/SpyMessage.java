/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles2projectserver;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Rui
 */
public class SpyMessage {
    
    public SpyMessage() {}

    public Document getDocument(String xmlString) { 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); DocumentBuilder builder;
        Document spyDoc = null;
    try {
        builder = factory.newDocumentBuilder();
        spyDoc = (Document) builder.parse( new InputSource( new StringReader( xmlString ) ) );
        } catch (ParserConfigurationException | SAXException | IOException e) { 
        }
    return spyDoc; 
    }
}
