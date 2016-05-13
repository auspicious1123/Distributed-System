package task2;

/*
Design discription:
1. Client generate a random number and cut 16 bytes as TEA key and Using RSA (hard code d and n) to encrypt key and send it to server send it to server.
2. Client get user information, password and location, using TEA encryption and send sever the cipher.
3. Client receive the feedback information from server.
4. Finished.
*/

import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Rui
 */
public class TCPSpyUsingTEAandPasswordsAndRSA {
    public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try{
                    
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out =new DataOutputStream(s.getOutputStream());
                        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                        
                        
                        // random generate 16 byte keys. 
                        Random rnd = new Random();
                        BigInteger p = new BigInteger(400,100,rnd);
                        String key = p.toString().substring(0,16);
                        BigInteger e = new BigInteger("65537");
                        BigInteger n = new BigInteger("18228373326962826466027094114596436"
                                                        + "3360837117939625072186530606240683315386525637309543368"
                                                        + "0409408367144216994206412762085037497658873054261251748"
                                                        + "6168845615269144903951275227605839638875277808897633735"
                                                        + "71621251849841588854816064293909821461793");

                        // send key to server to check first.
                        BigInteger m  = new BigInteger(key.getBytes()); // m is the original clear text
                        
                        BigInteger c = m.modPow(e, n);
                        byte[] keyC = c.toByteArray();
                        out.write(keyC);
                        out.flush();
                        
                        // read in user name and password.
                        String userInfor = "";
                        System.out.print("Enter your ID:");
                        userInfor += buff.readLine();
                        userInfor += " ";
                        System.out.print("Enter your Password:");
                        userInfor += buff.readLine();
                        userInfor += " ";
                        System.out.print("Enter your location:");
                        userInfor += buff.readLine();
                        
                        // encryption and send the encrytion bytes to server.
                        byte[] keys = key.getBytes();
                        TEA tea = new TEA(keys);
                        byte[] cipher = tea.encrypt(userInfor.getBytes());
                        out.write(cipher);   // write cipher to server
                        out.flush();
                        
                        System.out.println("Encryption finised and cipher send to server");
                        
                        
                        // read feed back information from server.
                        byte[] b = new byte[1024];
                        int nn = in.read(b);
                        if(nn < 0) {
                            return;
                        }
                        int i = 0;
                        byte[] cryptResult = new byte[nn];
                        while (i < nn) {
                            cryptResult[i] = b[i];
                            i++;
                        }
                        byte[] decryptResult = tea.decrypt(cryptResult);
                        System.out.println(new String(decryptResult));
                        
		}catch (UnknownHostException e){
                    System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){
                    System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){
                    System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){
                    System.out.println("close:"+e.getMessage());
                }
            }
     }
}
