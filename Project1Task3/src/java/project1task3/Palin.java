/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1task3;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rui
 */
@WebServlet(name = "Palin", urlPatterns = {"/Palin"})
public class Palin extends HttpServlet {

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
        String ua = request.getHeader("User-Agent");
        boolean mobile;
        String doctype =  "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.contains("Android")) || (ua.contains("iPhone")))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            doctype = "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">";
            
        } else {
            mobile = false;
            //doctype = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
            
        }
        request.setAttribute("doctype", doctype);
        boolean result;
        String inputText = request.getParameter("InputTextData");
        String nextView;
        if(inputText == null) {
            nextView = "index.jsp";
        } else {
            if(request.getParameter("InputTextData").length() == 0) {
            result = true;
            inputText = null;
            //printPage(response, result,inputText, doctype);
            } else {
                String str = inputText.toLowerCase();
                result = isPalindrome(str);
            }
            request.setAttribute("inputText", inputText);
            request.setAttribute("result", result);
            nextView = "result.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

    
    /**
     * Determine whether the input string is palindrome or not.
     * @param str
     * @return 
     */
    private boolean isPalindrome(String str) {
        if((str == null) || str.length() == 0) {
                return true;
            }
            int i = 0;
            int j = str.length() - 1;
            while(i < j) {
                while(!isLetter(str.charAt(i)) && (i < j)){
                    i++;
                }
                while(!isLetter(str.charAt(j)) && (i < j)){
                    j--;
                }
                if(str.charAt(i) != str.charAt(j)){
                    return false; 
                } else {
                    i++;
                    j--;
                }
            }
            return true;
    }
    
    /**
     * Judge the input string.
     * @param c
     * @return 
     */
    private boolean isLetter(char c) {
        if(((c >= 'a') && (c <= 'z'))) {
            return true;
        } else {
            return false;
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
