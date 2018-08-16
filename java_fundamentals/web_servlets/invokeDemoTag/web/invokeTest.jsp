<%-- 
 * Example found on location 4348 of 7943
 * Servlet & JSP: A Beginner's Tutorial 
 * e-book written by: 
 * Budi Kurniawan
 * 
 * Recoded by: Michael Vinson
 * Date: Augest 14, 2018
--%>

<%@ taglib prefix="easy" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head><title>Product Details</title></head>
    <body>
        <easy:invokeDemo>
            <jsp:attribute name="productDetails">
                <table width="220" border="1">
                    <tr>
                        <td><b>Product Name</b></td>
                        <td>${productName}</td>
                    </tr>
                    <tr>
                        <td><b>Description</b></td>
                        <td>${description}</td>
                    </tr>
                    <tr>
                        <td><b>Price</b></td>
                        <td>${price}</td>
                    </tr>
                </table>
            </jsp:attribute>
        </easy:invokeDemo>
    </body>
</html>