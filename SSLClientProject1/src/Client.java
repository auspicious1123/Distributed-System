/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rui
 */
import java.io.*;
import javax.net.ssl.*;
import java.net.*;
import java.util.Scanner;
import javax.net.*;

public class Client {
    
    public static void main(String args[]) {
        
        int port = 6502;
        try {
            // tell the system who we trust
            System.setProperty("javax.net.ssl.trustStore","myCool.truststore");
            // get an SSLSocketFactory
            SocketFactory sf = SSLSocketFactory.getDefault();
            
            // an SSLSocket "is a" Socket
            Socket s = sf.createSocket("localhost",6502);
            
            
            BufferedWriter out = new BufferedWriter(
                                                    new OutputStreamWriter(
                                                                           s.getOutputStream()));
            BufferedReader in = new 
            BufferedReader(
                           new InputStreamReader(
                                                 s.getInputStream()));
            String userNameInfo = in.readLine();
            System.out.println(userNameInfo);
            Scanner scanner = new Scanner(System.in);
            String userName = scanner.nextLine();
            out.write(userName);
            out.newLine();
            out.flush();
            
            String passWordInfo = in.readLine();
            System.out.println(passWordInfo);
            String passWord = scanner.nextLine();
            
            
            out.write(passWord);
            out.newLine();
            out.flush();
            String answer = in.readLine();               
            System.out.println(answer);
            out.close(); 
            in.close();
        }
        catch(Exception e) {
            System.out.println("Exception thrown " + e);
        }
    }
} 
