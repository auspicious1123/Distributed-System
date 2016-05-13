package lab4;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServerTCP {
    
    private static String parseFilename(String line) {
        return line.split(" ")[1];
    }
    
    private static String readFile(String fileName) throws FileNotFoundException {
        BufferedReader buff = new BufferedReader(new FileReader(fileName));
        String page = "";
        String line;
        try {
            line = buff.readLine();
            while(line != null) {
                page += line;
                line = buff.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port we are using
            
            // Create a new server socket
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("This is the socket >>>>>>>>>>>\n");
            
            /*
             * Block waiting for a new connection request from a client.
             * When the request is received, "accept" it, and the rest
             * the tcp protocol handshake will then take place, making 
             * the socket ready for reading and writing.
             */
            clientSocket = listenSocket.accept();
            // If we get here, then we are now connected to a client.
            // Set up "in" to read from the client socket
            
            Scanner in;
            in = new Scanner(clientSocket.getInputStream());
            String line = in.nextLine();
            String filename = "." + parseFilename(line);
            System.out.println("GET" + filename + "..........");
            line = in.nextLine();
            while(line.length() != 0) {
                line = in.nextLine();
            }
                   
            // Set up "out" to write to the client socket
            PrintWriter out;
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
            String page = null;
            try {
                page = readFile(filename);
            } catch(FileNotFoundException e) {
                System.out.println("HTTP/1.1 404 OK\n\n");
                out.println("HTTP/1.1 404 OK\n\n");
                out.println("404: FileNotFound!");
                out.flush();
                out.close();
            }
            
            if(page != null) {
                System.out.println("HTTP/1.1 200 OK\n\n");
                out.println("HTTP/1.1 200 OK\n\n");
                out.println(page);
                out.flush();
                out.close();
            }
            
            
//            /* 
//             * Forever,
//             *   read a line from the socket
//             *   print it to the console
//             *   echo it (i.e. write it) back to the client
//             */
//            while (true) {
//                String data = in.nextLine();
//                System.out.println("Echoing: " + data);
//                out.println(data);
//                out.flush();
//            }
            
        // Handle exceptions
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());
            
        // If quitting (typically by you sending quit signal) clean up sockets
//        } finally {
//            try {
//                if (clientSocket != null) {
//                    clientSocket.close();
//                }
//            } catch (IOException e) {
//                // ignore exception on close
//            }
        }
    }
}

