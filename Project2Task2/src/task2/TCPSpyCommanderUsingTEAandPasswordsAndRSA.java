package task2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Hashtable;

/*
Design discription:
1. Server receives the TEA key and save it.
2. Server receives user information from client and using key in 1 to decryt it.
3. Check the  decryption information, if they are not ASCII, ignore the request and show attack information.
   If the decrytion is included in database table, send back right information to client. And update kml file.
4. Finished.
*/
public class TCPSpyCommanderUsingTEAandPasswordsAndRSA {
    public static void main(String args[]) {
        try {
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    static int countNum = 0;
    static String jamesbPosition = "-79.940450,40.437394,0.0000";
    static String joemPosition = "-79.945389,40.444216,0.00000";
    static String mikemPosition = "-79.948460,40.444501,0.00000";
    static String seanbPosition = "-79.945289,40.44431,0.00000";
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            Hashtable<String, String> table = new Hashtable<String, String>();
            table.put("jamesb", "f8c24895c4e60ec58d89b4d382964f9e57849b8a"); // salt = I
            table.put("joem","50e60f9dec1202808d28a732237ce93ed22ead64");      // salt = love
            table.put("mikem", "93f0329fb966cb25d64c837a37b8bf7e01cbcfd2");    // salt = you
            table.put("seanb","c50cd6711869dafb8696b640e9db414bd6d55e78");    // salt = hate  all password are handout values

            BigInteger d = new BigInteger("196226824269836490406672793961392584"
                    + "87735262020905059497321717915498432794481565699052877939"
                    + "61941503303851396024572689700607770861283877870137622059"
                    + "45176279156569593279484386310393701690714378488060881336"
                    + "543386237901392551618839164053995473");
            BigInteger n = new BigInteger("18228373326962826466027094114596436"
                    + "3360837117939625072186530606240683315386525637309543368"
                    + "0409408367144216994206412762085037497658873054261251748"
                    + "6168845615269144903951275227605839638875277808897633735"
                    + "71621251849841588854816064293909821461793");
            
            byte[] keyByte = new byte[1024];
            int keyByteNum = in.read(keyByte);
            if(keyByteNum < 0)
                return;
            int keyI = 0;
            byte[] ckey = new byte[keyByteNum];
            while (keyI < keyByteNum) {
                ckey[keyI] = keyByte[keyI];
                keyI++;
            }
            // using RSA decrypt
            BigInteger c = new BigInteger(ckey);
            BigInteger clear = c.modPow(d,n);
            String Key = new String(clear.toByteArray());
            
            // read in encryption input data from client.
            System.out.println("Server is running...");
            countNum++;
            byte[] b = new byte[1024];
            int num = in.read(b);
            if(num < 0)
                return;
            int i = 0;
            byte[] crypt = new byte[num];
            while (i < num) {
                crypt[i] = b[i];
                i++;
            }
            
            TEA tea = new TEA(Key.getBytes());
            
            //Decrypt input data get infor bytes and plain string.
            byte[] plain = tea.decrypt(crypt);
            String decryptString = new String(plain);

            // Check whether it is ASCII
            for (int m = 0; m < decryptString.length(); m++) {
                if (decryptString.charAt(m) > 128) {
                    System.out.println("Got visit " + countNum + ",illegal symmetric key used. This may be an attack.");
                    return;
                }
            }
                
            // Check whether it is valid user name and password
            String[] user_password = decryptString.split(" ");
//            String userName = user_password[0];
//            String passWord = user_password[1];
//            String position = user_password[2];
            
            switch(user_password[0]) {
                case "jamesb": 
                    user_password[1] = user_password[1] + "I";
                    break;
                case "joem":
                    user_password[1] = user_password[1] + "love";
                    break;
                case "mikem":
                    user_password[1] = user_password[1] + "you";
                    break;
                case "seanb":
                    user_password[1] = user_password[1] + "hate";
                    break;
                default:
                    break;
            }
            
            
            // calculate password + salt hash values
            PasswordHash hash = new PasswordHash();
            user_password[1] = hash.calculateHashValue(user_password[1]);
            String result;
            String content;
            FileWriter writer = new FileWriter("SecretAgents.kml");
            content = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<kml xmlns=\"http://earth.google.com/kml/2.2\"\n" +
            "><Document>\n" +
            "<Style id=\"style1\">\n" +
            "<IconStyle>\n" +
            "<Icon>\n" +
            "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>\n" +
            "</Icon> </IconStyle> </Style> <Placemark>\n" +
            "<name>seanb</name>\n" +
            "<description>Spy Commander</description> <styleUrl>#style1</styleUrl>\n" +
            "<Point>\n" +
            "<coordinates>-79.945289,40.44431,0.00000</coordinates> </Point>\n" +
            "</Placemark> <Placemark>\n" +
            "<name>jamesb</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
            "<coordinates>-79.940450,40.437394,0.0000</coordinates> </Point>\n" +
            "</Placemark>\n" +
            "6\n" +
            "<Placemark> <name>joem</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
            "<coordinates>-79.945389,40.444216,0.00000</coordinates> </Point>\n" +
            "</Placemark>\n" +
            "<Placemark> <name>mikem</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
            "<coordinates>-79.948460,40.444501,0.00000</coordinates> </Point>\n" +
            "</Placemark>\n" +
            "</Document>\n" +
            "</kml>";
            
            byte[] feedbackInfor;
            if(table.containsKey(user_password[0]) && table.get(user_password[0]).equals(user_password[1])) {
                if(table.get(user_password[0]).equals(user_password[1])) {
                    System.out.println("Got visit " + countNum + " from " + user_password[0]);
                    /*
                    update the changing location according to user name.
                    */
                    switch(user_password[0]) {
                        case "jamesb": 
                            jamesbPosition = user_password[2];
                            break;
                        case "joem": 
                            joemPosition = user_password[2];
                            break;
                        case "mikem": 
                            mikemPosition = user_password[2];
                            break;
                        case "seanb": 
                            seanbPosition = user_password[2];
                            break;
                        default:
                            break;
                    }
                    /*
                    update the four spy locations, if did not change will use the previous location.
                    */
                    content = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<kml xmlns=\"http://earth.google.com/kml/2.2\"\n" +
                    "><Document>\n" +
                    "<Style id=\"style1\">\n" +
                    "<IconStyle>\n" +
                    "<Icon>\n" +
                    "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>\n" +
                    "</Icon> </IconStyle> </Style> <Placemark>\n" +
                    "<name> seanb </name>\n" +
                    "<description>Spy Commander</description> <styleUrl>#style1</styleUrl>\n" +
                    "<Point>\n" +
                    "<coordinates>" + seanbPosition + "</coordinates> </Point>\n" +
                    "</Placemark> <Placemark>\n" +
                    "<name>jamesb</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                    "<coordinates>" + jamesbPosition + "</coordinates> </Point>\n" +
                    "</Placemark>\n" +
                    "6\n" +
                    "<Placemark> <name>joem</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                    "<coordinates>" + joemPosition + "</coordinates> </Point>\n" +
                    "</Placemark>\n" +
                    "<Placemark> <name>mikem</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                    "<coordinates>" + mikemPosition + "</coordinates> </Point>\n" +
                    "</Placemark>\n" +
                    "</Document>\n" +
                    "</kml>";
                    
                    writer.write(content);
                    writer.flush();
                    writer.close();
                    
                    System.out.println("sean: " + seanbPosition);
                    System.out.println("jamesb: " + jamesbPosition);
                    System.out.println("joem: " + joemPosition);
                    System.out.println("mikem; " + mikemPosition);
                    
                    System.out.println("Finish writing file.");
                                                
                    result = "Thank you. Your location was securely transmitted to Intelligence Headquarters.";
                } else {
                    System.out.println("Got visit " + countNum +  " from " + user_password[0] 
                            + ",Illegal Password attempt. This may be an attack.");
                    result = "Not a valid user-id or password.";
                    writer.write(content);
                    writer.flush();
                    writer.close();
                }
                feedbackInfor = tea.encrypt(result.getBytes());
                out.write(feedbackInfor);
            } else {
                System.out.println("Got visit " + countNum +  " from " + user_password[0] 
                        + ", not a valid user-id or password"
                        + ", this may be an attack.");
                result = "Not a valid user-id or password.";
                feedbackInfor = tea.encrypt(result.getBytes());
                out.write(feedbackInfor);
            }
            
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /* close failed */}
        }
    }
    
}
