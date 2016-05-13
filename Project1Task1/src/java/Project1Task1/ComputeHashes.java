/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project1Task1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Rui
 */
@WebServlet(name = "ComputeHashes", urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            String title = "Using GET method read content from web Site";
        String docType = 
                "<!DOCTYPE html public \"-//w3c//dtd html 4.0 " +
                "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1>" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>The input text is: </b>"
                + request.getParameter("InputTextData") + "\n" +
                "  <li><b>Hash function is: </b>"
                + request.getParameter("method") + "\n" +
                "</ul>\n" +
                "</body></html>");
        
        String inputText = request.getParameter("InputTextData");  // save input value
        String hashMethod = request.getParameter("method");        // get hash function method
        byte[] hashBinaryValue = calculateHashBinaryValue(inputText, hashMethod);
        String hexString = null;
        try {
            hexString = getHexString(hashBinaryValue);
            //System.out.print("(Hex):" + hexString);
        } catch (Exception ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String encodeString = getEncodeString(hashBinaryValue);
        System.out.println("(Base 64):" + encodeString);
        
        printPage(response, hashMethod, hexString, encodeString);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }
    
    /**
     * According to the input text to calculate its hash values.
     * @param inputText
     * @param method
     * @return 
     */
    public byte[] calculateHashBinaryValue(String inputText, String method) {
            byte[] buffer = inputText.getBytes();
            byte[] result;
            MessageDigest hash;
            try {
                hash = MessageDigest.getInstance(method);
                hash.reset();
                hash.update(buffer);
                result = hash.digest();
                return result;
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Exception caught: " + ex);
            } catch (Exception ex) {
                System.out.println("HexString Exception " + ex);
        }
        return null;
    }
    
    /**
     * Change hash value into hexString format.
     * @param b
     * @return
     * @throws Exception 
     */
    public String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring( 1 );
        }
        return result; 
    }
    
    /**
     * Print the result page and show the results.
     * @param response
     * @param method
     * @param hexString
     * @param encodeString 
     */
    public void printPage(HttpServletResponse response, String method, String hexString, String encodeString) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html");
            out = response.getWriter();
            String title = "Input text hash values calculated by user's chosen hash function";
            String docType =
                    "<!DOCTYPE html public \"-//w3c//dtd html 4.0 " +
                    "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body>\n" +
                    "<h1>" + title + "</h1>\n" +
                    "<ul>\n" +
                    "  <li><b>"+ method +" </b>"
                    + hexString + "\n" +
                    "  <li><b>"+ method +" </b>"
                    + encodeString + "\n" +
                    "</ul>\n" +
                    "</body></html>");
        } catch (IOException ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Change hash values into encode format.
     * @param data
     * @return 
     */
    public String getEncodeString(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encodeBuffer(data);
        return base64.substring(0, base64.length());
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
