/*
 * Example found on location 4614 of 7943
 * Servlet & JSP: A Beginner's Tutorial 
 * e-book written by: 
 * Budi Kurniawan
 * 
 * Recoded by: Michael Vinson
 * Date: Augest 15, 2018
*/

package listenerdemo.listener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class PrefStatListener implements ServletRequestListener {
    
    @Override
    public void requestInitialized(ServletRequestEvent sre){
        ServletRequest servletRequest = sre.getServletRequest();
        servletRequest.setAttribute("start", System.nanoTime());
    }
    
    @Override
    public void requestDestroyed(ServletRequestEvent src){
        ServletRequest servletRequest = src.getServletRequest();
        Long start = (Long) servletRequest.getAttribute("start");
        Long end = System.nanoTime();
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        System.out.println("time taken to execute " + uri 
                + ":" + ((end - start)/1000) + "microseconds");
    }
    
}
