/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles2projectclient;

/**
 *
 * @author Rui
 */
public class WebServiceDesignStyles2ProjectClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Note: There is NO communication code or XML handling code in the main
        // routine. That important work is done in spyOperation. We are separating // concerns with a proxy design.
        String result = "";
        System.out.println("Adding spy jamesb");
        // create a spy
        Spy bond = new Spy("jamesb", "spy", "London","james"); // create a message
        SpyMessage sb = new SpyMessage(bond,"addSpy");
        // make a call on the web service
        result = spyOperation(sb.toXML()); 
        System.out.println(result);
        System.out.println("Adding spy seanb");
        Spy beggs = new Spy("seanb", "spy master", "Pittsburgh","sean"); 
        SpyMessage ss = new SpyMessage(beggs,"addSpy");
        result = spyOperation(ss.toXML());
        System.out.println(result);
        System.out.println("Adding spy joem");
        Spy mertz = new Spy("joem", "spy", "Los Angeles","joe"); 
        SpyMessage sj = new SpyMessage(mertz,"addSpy");
        result = spyOperation(sj.toXML()); System.out.println(result);
        System.out.println("Adding spy mikem");
        Spy mccarthy = new Spy("mikem", "spy", "Ocean City Maryland","sesame"); 
        SpyMessage sm = new SpyMessage(mccarthy,"addSpy");
        result = spyOperation(sm.toXML());

        System.out.println(result);
        System.out.println("Displaying spy list");
        SpyMessage list = new SpyMessage(new Spy(),"getList"); 
        result = spyOperation(list.toXML()); System.out.println(result);
        System.out.println("Displaying spy list as XML");
        SpyMessage listXML = new SpyMessage(new Spy(),"getListAsXML"); 
        result = spyOperation(listXML.toXML());
        System.out.println(result);
        System.out.println("Updating spy jamesb");
        Spy newJames = new Spy("jamesb","Cool Spy","New Jersey","sesame"); 
        SpyMessage um = new SpyMessage(newJames,"updateSpy");
        result = spyOperation(um.toXML());
        System.out.println(result);
        System.out.println("Displaying spy list");
        list = new SpyMessage(new Spy(),"getList"); 
        result = spyOperation(list.toXML()); 
        System.out.println(result);
        System.out.println("Deleting spy jamesb");
        Spy james = new Spy("jamesb");
        SpyMessage dm = new SpyMessage(james,"deleteSpy"); 
        result = spyOperation(dm.toXML()); 
        System.out.println(result);

        System.out.println("Displaying spy list");
        list = new SpyMessage(new Spy(),"getList"); 
        result = spyOperation(list.toXML()); 
        System.out.println(result);
        System.out.println("Displaying spy list as XML"); 
        listXML = new SpyMessage(new Spy(),"getListAsXML"); 
        result = spyOperation(listXML.toXML()); 
        System.out.println(result);
        System.out.println("Deleting spy Amos");
        Spy amos = new Spy("amos");
        SpyMessage am = new SpyMessage(amos,"deleteSpy"); 
        result = spyOperation(am.toXML()); 
        System.out.println(result);
    }

    private static String spyOperation(java.lang.String inputString) {
        webservicedesignstyles2projectserver.Spy2Service_Service service = 
                new webservicedesignstyles2projectserver.Spy2Service_Service();
        webservicedesignstyles2projectserver.Spy2Service port = 
                service.getSpy2ServicePort();
        return port.spyOperation(inputString);
    }
    
}
