/*
 * Example found on location 3232 of 7943
 * Servlet & JSP: A Beginner's Tutorial 
 * e-book written by: 
 * Budi Kurniawan
 * 
 * Recoded by: Michael Vinson
 * Date: Augest 9, 2018
*/
package jstldemo.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BigCitiesServlet", urlPatterns = {"/bigCities"})
public class BigCitiesServlet extends HttpServlet {
    private static final int serialVersionUID = 112233;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> capitals = new HashMap<String, String>();
        capitals.put("Indonesia", "Jakarta");
        capitals.put("Malaysia", "Kuala Lumpur");
        capitals.put("Thailand", "Bangkok");
        request.setAttribute("capitals", capitals);
        
        Map<String, String[]> bigCities = new HashMap<String, String[]>();
        bigCities.put("Australia", new String[] {"Sydney", "Melbourne", 
                "Perth"});
        bigCities.put("New Zeland", new String [] {"Auckland", "Christchurch",
                "Wellington"});
        bigCities.put("Indonesia", new String[] {"Jakarta", "Surabaya",
                "Medan"});
        
        request.setAttribute("capitals", capitals);
        request.setAttribute("bigCities", bigCities);
        RequestDispatcher rd =
                request.getRequestDispatcher("/bigCities.jsp");
        rd.forward(request, response);
    }
}
