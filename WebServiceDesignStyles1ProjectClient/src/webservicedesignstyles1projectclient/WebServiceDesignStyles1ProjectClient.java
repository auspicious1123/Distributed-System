/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles1projectclient;

/**
 *
 * @author Rui
 */
public class WebServiceDesignStyles1ProjectClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(getList()); 
        System.out.println(getListAsXML()); 
        addSpy("mikem","spy","Pittsburgh","sesame"); 
        addSpy("joem","spy","North Hills","xyz"); 
        addSpy("seanb","spy commander","South Hills","abcdefg"); 
        addSpy("jamesb","spy","Adelaide","sydney"); 
        addSpy("adekunle","spy","Pittsburgh","secret"); 
        System.out.println(getList()); 
        System.out.println(getListAsXML());
        updateSpy("mikem", "super spy", "Pittsburgh","sesame"); 
        System.out.println(getListAsXML());
        String result = getSpy("jamesb"); 
        System.out.println(result);
        deleteSpy("jamesb"); 
        result = getSpy("jamesb"); 
        System.out.println(result); 
    }

    private static String addSpy(java.lang.String name, java.lang.String password, java.lang.String title, java.lang.String location) {
        cmu.edu.andrew.rw1.SpyService_Service service = new cmu.edu.andrew.rw1.SpyService_Service();
        cmu.edu.andrew.rw1.SpyService port = service.getSpyServicePort();
        return port.addSpy(name, password, title, location);
    }

    private static String deleteSpy(java.lang.String name) {
        cmu.edu.andrew.rw1.SpyService_Service service = new cmu.edu.andrew.rw1.SpyService_Service();
        cmu.edu.andrew.rw1.SpyService port = service.getSpyServicePort();
        return port.deleteSpy(name);
    }

    private static String getList() {
        cmu.edu.andrew.rw1.SpyService_Service service = new cmu.edu.andrew.rw1.SpyService_Service();
        cmu.edu.andrew.rw1.SpyService port = service.getSpyServicePort();
        return port.getList();
    }

    private static String getListAsXML() {
        cmu.edu.andrew.rw1.SpyService_Service service = new cmu.edu.andrew.rw1.SpyService_Service();
        cmu.edu.andrew.rw1.SpyService port = service.getSpyServicePort();
        return port.getListAsXML();
    }

    private static String getSpy(java.lang.String name) {
        cmu.edu.andrew.rw1.SpyService_Service service = new cmu.edu.andrew.rw1.SpyService_Service();
        cmu.edu.andrew.rw1.SpyService port = service.getSpyServicePort();
        return port.getSpy(name);
    }

    private static String updateSpy(java.lang.String name, java.lang.String password, java.lang.String title, java.lang.String location) {
        cmu.edu.andrew.rw1.SpyService_Service service = new cmu.edu.andrew.rw1.SpyService_Service();
        cmu.edu.andrew.rw1.SpyService port = service.getSpyServicePort();
        return port.updateSpy(name, password, title, location);
    }
    
    
}
