/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1task2;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rui
 */
@WebServlet(name = "BigCalc", urlPatterns = {"/BigCalc"})
public class BigCalc extends HttpServlet {

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
        if((request.getParameter("Integer1").length() == 0) || 
               (request.getParameter("Integer2").length() == 0) ||
               (request.getParameter("Operation").length() == 0)) {
                printErrorPage(response);
            } else {
            BigInteger Integer1 = new BigInteger(request.getParameter("Integer1"));
            BigInteger Integer2 = new BigInteger(request.getParameter("Integer2"));
            String operation = request.getParameter("Operation");
//            System.out.println(Integer1);
//            System.out.println(Integer2);
//            System.out.println(operation);
            
            // calculation based on the given operation.
            BigInteger result = null;
            if(operation.equals("add")) {              // add
                result = Integer1.add(Integer2);
            } else if(operation.equals("multiply")) {
                result = Integer1.multiply(Integer2);
            } else if(operation.equals("relativelyPrime")) {
                result = Integer1.gcd(Integer2);
            } else if(operation.equals("mod")) {        // mod
                
                if(Integer2.equals(BigInteger.ZERO)){        
                    //System.out.println("Error: mod zero.");
                    printErrorPage(response);    // deal with the exception, print error page.
                } else {
                    result = Integer1.mod(Integer2);
                }
                
            } else if(operation.equals("modInverse")) { // modInverse
                if((Integer2.compareTo(BigInteger.ZERO) <= 0) ||  (!(Integer1.gcd(Integer2.abs()).compareTo(BigInteger.ONE) == 0))) {
                    //System.out.println("Error: mod zero.");
                    printErrorPage(response);
                } else {
                    result = Integer1.modInverse(Integer2);
                }
            } else if(operation.equals("power")) {         // power
                result = Integer1.pow(Integer2.intValueExact()); 
            } else {
                //System.out.println("Error message to browser."); // HTML to print error message
                printErrorPage(response);
            } 
            printPage(response,Integer1, Integer2, operation, result);
        }
    }
    
    /**
     * print the result page and show the result.
     * @param response
     * @param Integer1
     * @param Integer2
     * @param operation
     * @param result 
     */
    
    public void printPage(HttpServletResponse response, BigInteger Integer1, BigInteger Integer2, String operation, BigInteger result) {
        try {
            PrintWriter out = null;
            response.setContentType("text/html");
            out = response.getWriter();
            String title = "Calculation result: ";
            String docType =
                    "<!DOCTYPE html public \"-//w3c//dtd html 4.0 " +
                    "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body>\n" +
                    "<h1>" + title + "</h1>\n" +
                    "<ul>\n" +
                    "<li>Input integer 1 is : " + "<b>" + Integer1 + "</b>" +
                    "<li>Input integer 2 is : " + "<b>" + Integer2 + "</b>" +
                    "<li>Operation is : " + "<b>" + operation + "</b>" +
                    "<li>Result is : " + "<b>" + result + "</b>" +
                    "</ul>\n" +
                    "</body></html>");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(BigCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Set the error page.
     * @param response 
     */
    public void printErrorPage(HttpServletResponse response){
        try {
            PrintWriter out = null;
            response.setContentType("text/html");
            out = response.getWriter();
            String title = "Error page: ";
            String docType =
                    "<!DOCTYPE html public \"-//w3c//dtd html 4.0 " +
                    "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body>\n" +
                    "<h1>" + title + "</h1>\n" +
                    "<ul>\n" +
                    "<li><b>There is an error. please check the input Integer values and operation: </b>" +
                    "<li><b>if any input is null, it would be error. </b>" +
                    "<li><b>if operation is mod, input integer 2 is zero would be error.</b>" +
                    "<li><b>if operation is modInverse, input integer 2 is less than 0, </b>" +
                    "    <b>or integer 1 is not relatively prime to integer 2, it would be error.</b>" +
                    "<li><b> Or check the operation, it may not in the list would be error. </b>" + 
                    "</ul>\n" +
                    "</body></html>");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(BigCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
