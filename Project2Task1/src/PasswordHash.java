
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rui
 */
public class PasswordHash {
    
    // calculate hash value.
    public String calculateHashValue(String inputText) {
            byte[] buffer = inputText.getBytes();
            byte[] result;
            String resultString = "";
            String method = "SHA-1";
            MessageDigest hash;
            try {
                hash = MessageDigest.getInstance(method);
                hash.reset();
                hash.update(buffer);
                result = hash.digest();
                resultString = getHexString(result);
                return resultString;
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Exception caught: " + ex);
            } catch (Exception ex) {
                System.out.println("HexString Exception " + ex);
        }
            return resultString;
    }
    
    // change to hex string
    public String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring( 1 );
        }
        return result; 
    }
}
