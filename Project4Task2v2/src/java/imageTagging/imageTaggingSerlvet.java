/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageTagging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is project task2 server, it is required input a image network url. I write a
 * web page to test the responce of my AlchomyAPI. You can use the following usl to input:
 * http://d2472bm234ooxx.cloudfront.net/wp-content/uploads/2014/03/be-wary-of-whose-opinion-you-cou.jpg
 * 
 * have deploy it to Kerobu.
 * 
 * @author Rui
 * 
 */
//@WebServlet(name = "imageTaggingServlet", urlPatterns = {"/ImageTaggingServlet"})
public class imageTaggingSerlvet extends HttpServlet {

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
        
        // Android send an image url string as the input.
        String imageURL = request.getParameter("imageURL");
        System.out.println("****************************************" + imageURL);
        String nextView = null;
        if (imageURL == null) {
            nextView = "inputURL.jsp";
            RequestDispatcher view = request.getRequestDispatcher(nextView);
            view.forward(request, response);
        } else {
            // Getting imageURL, get image tagging through the third party API: 
            // AlchemyAPI Image Tagging API
            String researchURL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedImageKeywords?"
                    + "apikey=fa43ab218e1c693c41c3c3d80774b100941101e6&outputMode=xml&url="
                    + imageURL;
            
            System.out.println("*********" + researchURL);
            String resultString = vistWeb(researchURL);
            System.out.println(resultString);
            
            PrintWriter out = response.getWriter();
            
            out.println(resultString);
        }
    }
    /**
     * Through third party to get the image tag of given image url.
     * @param researchURL
     * @return 
     */
    private String vistWeb(String researchURL) {
        String response = "";
        try {
            URL url = new URL(researchURL);
            /* 
             * Create an HttpURLConnection. visit given url website.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String tmp;
            int i = 0;
            while ((tmp = in.readLine()) != null) {
                response += tmp + "\n";
                i++;
            }
            in.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(imageTaggingSerlvet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(imageTaggingSerlvet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        return response;
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
