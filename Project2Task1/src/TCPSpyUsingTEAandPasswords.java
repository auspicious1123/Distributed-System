/**
 * @author Rui
 * 
 * Design description:
 * 1.Client get TEA key,using it encrypt the input user id, password , location, send it to server.
 * 2. Server has a database save the user id salt and "password + salt" hash value.
 * 3. When server get user name and password, it calculate the hash value of password + salt and check whether it is valid.
 * 4. if the decrypt information is not ASCII, ignore it and show server wrong key information.
 * 4. if check it is value, send back right information to client and update location in kml file.
 * 5. if user id and password are not match, send back wrong password information.
 */
import java.net.*;
import java.io.*;

/**
 *
 * @author Rui
 */
public class TCPSpyUsingTEAandPasswords {
    public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);
                        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                        // read in encryption key.
                        System.out.println("Enter symmetric key for TEA (taking first sixteen bytes):");
                        String keyInput = buff.readLine();
                        String key = keyInput.substring(0, 16);  // just taking first sixteen bytes.
//                        System.out.println("Debug key");
//                        System.out.println(key);
                        byte[] keys = key.getBytes();
                        TEA tea = new TEA(keys);
                        
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
                        
                        // encryption
                        byte[] cipher = tea.encrypt(userInfor.getBytes());
//                        System.out.println("encry data:");
//                        for(int i=0;i<cipher.length;i++) {
//                            System.out.println(cipher[i]);
//                        }
                        
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out =new DataOutputStream(s.getOutputStream());
                        
//                        System.out.println("Encrypt data:");
//                        for(int i = 0; i < cipher.length; i++) {
//                            System.out.println(cipher[i]);
//                        }
                        out.write(cipher);   // write cipher to server
                        out.flush();
                        
                        System.out.println("Encryption finised and cipher send to server");
                        
                        //String data = in.read();
//                        byte[] b = new byte[1024];
//			int n = in.read(b);
//                        if(n < 0)
//                            return;
//                        int i = 0;
//                        byte[] plain = new byte[n];
//                        while(i < n) {
//                            plain[i] = b[i];
//                            i++;
//                        }
//                        System.out.println("Received: "+ new String(plain));
                        
                        // read feed back information from server.
                        byte[] b = new byte[1024];
                        int n = in.read(b);
                        if(n < 0) {
                            return;
                        }
                        int i = 0;
                        byte[] cryptResult = new byte[n];
                        while (i < n) {
                            cryptResult[i] = b[i];
                            i++;
                        }
                        byte[] decryptResult = tea.decrypt(cryptResult);
                        System.out.println(new String(decryptResult));
                        out.close();
                        in.close();
                        
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
