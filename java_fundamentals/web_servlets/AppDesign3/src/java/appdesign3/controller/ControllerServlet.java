/*
 * Example found on location 5165 of 7943
 * Servlet & JSP: A Beginner's Tutorial 
 * e-book written by: 
 * Budi Kurniawan
 * 
 * Recoded by: Michael Vinson
 * Date: Augest 16, 2018
*/

package appdesign3.controller;
import java.util.List;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import appdesign3.action.SaveProductAction;
import appdesign3.form.ProductForm;
import appdesign3.validator.ProductValidator;
import appdesign3.model.Product;
import java.math.BigDecimal;


@WebServlet (name = "ConrollerServlet", urlPatterns= {"/input-product",
            "/save-product" } )


public class ControllerServlet extends HttpServlet {
    
    private static final long serialVersionUID = 98279L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
       process(request, response);
    }
    
    private void process(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {
    
        String uri = request.getRequestURI();
        /*
         * uri is in this form: /contextName/resourceName,
         * for example: /appdesign1/input-product.
         * However, in the event of a default context, the
         * context name is empty, and uri has this form
         * /resourceName, e.g.: /input-product
         */
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        // execute an action
        String dispatchUrl = null;
        
        if ("input-product".equals(action)) {
            // no action class, just forward
            dispatchUrl = "/jsp/ProductForm.jsp";
        } else if ("save-product".equals(action)){
            // instantiate action class
            ProductForm productForm = new ProductForm();
            // populate action properties
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(
                request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));
            
            // validate ProductForm
            ProductValidator productValidator = new ProductValidator();
            List<String> errors = productValidator.validate(productForm);
            if (errors.isEmpty()) {
                // create Product from ProductFrom
                Product product = new Product();
                product.setName(productForm.getName());
                product.setDescription(productForm.getDescription());
                product.setPrice(new BigDecimal(productForm.getPrice()));
                
                // no validation error, execute action method
                SaveProductAction saveProductAction = new SaveProductAction();
                saveProductAction.save(product);
                
                // store action in a scope variable for the view
                request.setAttribute("product", product);
                dispatchUrl = "/jsp/ProductDetails.jsp";
            } else {
                
                request.setAttribute("errors", errors);
                request.setAttribute("form", productForm);
                dispatchUrl = "/jsp/ProductForm.jsp";
            }
            
        }
        
        // forward to a view
        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
