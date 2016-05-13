/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles3projectclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class WebServiceDesignStyles3ProjectClient {

    //Create a new spy and add the spy to the SpyListCollection
    public static int doPut(Spy spy) {
        try {
            URL url = new URL("http://localhost:8080/WebServiceDesignStyles3ProjectServer/"
                    + "SpyListCollection/" + spy.getName());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            
            con.getOutputStream().write(spy.toXML().getBytes());   // write infor

            int status = con.getResponseCode();
            con.disconnect();
            return status;
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebServiceDesignStyles3ProjectClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebServiceDesignStyles3ProjectClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 404;
    }

    public static int doPost(Spy spy) { // do post request
        try {
            URL url = new URL("http://localhost:8080/WebServiceDesignStyles3ProjectServer/"
                    + "SpyListCollection");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(spy.toXML().getBytes());
            
            int status = con.getResponseCode(); // get status
            con.disconnect();
            return status;
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebServiceDesignStyles3ProjectClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebServiceDesignStyles3ProjectClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 404;
    }

    public static int doDelete(String name) {  // delete method
        int status = 0;
        try {
            URL url = new URL("http://localhost:8080/WebServiceDesignStyles3ProjectServer/"
                    + "SpyListCollection/" + name);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            status = conn.getResponseCode();   // get status
            conn.disconnect();
            return status;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 404;
    }

    public static String doGetListAsXML() {   // get getlist
        // Make an HTTP GET passing the name on the URL line

        String response = "";
        HttpURLConnection conn = null;
        int status = 0;

        try {
            // pass the name on the URL line
            URL url = new URL("http://localhost:8080/WebServiceDesignStyles3ProjectServer/"
                    + "SpyListCollection");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // tell the server what format we want back
            conn.setRequestProperty("Accept", "text/xml");

            // wait for response
            status = conn.getResponseCode();

            // If things went poorly, don't try to read any response, just return.
            if (status != 200) {
                return Integer.toString(conn.getResponseCode());
            }
            
            String output = "";
            // things went well so let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {  // out infor
                response += output;
            }
            conn.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    
    public static String doGetListAsText() {
        // Make an HTTP GET passing the name on the URL line

        String response = "";
        HttpURLConnection conn = null;
        int status = 0;

        try {
            // pass the name on the URL line
            URL url = new URL("http://localhost:8080/WebServiceDesignStyles3ProjectServer/"
                    + "SpyListCollection");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // tell the server what format we want back
            conn.setRequestProperty("Accept", "text/plain");

            // wait for response
            status = conn.getResponseCode();

            // If things went poorly, don't try to read any response, just return.
            if (status != 200) {
                return Integer.toString(conn.getResponseCode());
            }
            
            String output = "";
            // things went well so let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                response += output;
            }
            conn.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    

    public static String doGetSpyAsXML(String name) {
        // Make an HTTP GET passing the name on the URL line

        String response = "";
        HttpURLConnection conn = null;
        int status = 0;

        try {
            // pass the name on the URL line
            URL url = new URL("http://localhost:8080/WebServiceDesignStyles3ProjectServer/"
                    + "SpyListCollection/" + name);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/xml");
            // tell the server what format we want back

            // wait for response
            status = conn.getResponseCode();

            // If things went poorly, don't try to read any response, just return.
            if (status != 200) {
                return Integer.toString(conn.getResponseCode());
            }
            
            String output = "";
            // things went well so let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                response += output;
            }
            conn.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String doGetSpyAsText(String name) {
        // Make an HTTP GET passing the name on the URL line

        String response = "";
        HttpURLConnection conn = null;
        int status = 0;

        try {
            // pass the name on the URL line
            URL url = new URL("http://localhost:8080/WebServiceDesignStyles3ProjectServer/"
                    + "SpyListCollection/" + name);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // tell the server what format we want back
            conn.setRequestProperty("Accept", "text/plain");

            // wait for response
            status = conn.getResponseCode();

            // If things went poorly, don't try to read any response, just return.
            if (status != 200) {
                return Integer.toString(status);
            }
            String output = "";
            // things went well so let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                response += output;
            }
            conn.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Begin main");
        Spy spy1 = new Spy("mikem", "spy", "Pittsburgh", "sesame");
        Spy spy2 = new Spy("joem", "spy", "Philadelphia", "obama");
        Spy spy3 = new Spy("seanb", "spy commander", "Adelaide", "pirates");
        Spy spy4 = new Spy("jamesb", "007", "Boston", "queen");

        System.out.println(doPut(spy1)); // 201
        System.out.println(doPut(spy2)); // 201
        System.out.println(doPut(spy3)); // 201
        System.out.println(doPut(spy4)); // 201
        System.out.println(doDelete("joem")); // 200
        spy1.setPassword("Doris");
        System.out.println(doPost(spy1)); // 200

        System.out.println(doGetListAsXML()); // display xml
        System.out.println(doGetListAsText()); // display text

        System.out.println(doGetSpyAsXML("mikem")); // display xml
        System.out.println(doGetSpyAsText("joem")); // 404

        System.out.println(doGetSpyAsXML("mikem")); // display xml
        System.out.println(doPut(spy2)); // 201
        System.out.println(doGetSpyAsText("joem")); // display text
        System.out.println("End main");

    }

}
