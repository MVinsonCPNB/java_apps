/*
 * Example found on location 4409 of 7943
 * Servlet & JSP: A Beginner's Tutorial 
 * e-book written by: 
 * Budi Kurniawan
 * 
 * Recoded by: Michael Vinson
 * Date: Augest 14, 2018
*/

package listenerdemo.listener;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener,
    ServletContextListener{
        
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userCounter", new AtomicInteger());
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce){
    }
    
    @Override
    public void sessionCreated(HttpSessionEvent se){
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger) servletContext
                .getAttribute("userCounter");
        int userCount = userCounter.incrementAndGet();
        System.out.println("userCount incremented to: " + userCount);
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger) servletContext
                .getAttribute("userCounter");
        int userCount = userCounter.decrementAndGet();
        System.out.println("---------- userCount decremented to:" + userCount);
    }
    
}
