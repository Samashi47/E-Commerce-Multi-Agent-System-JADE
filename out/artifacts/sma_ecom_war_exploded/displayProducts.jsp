<%--
  Created by IntelliJ IDEA.
  User: ah-ma
  Date: 13/04/2024
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sma.ecom.entities.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sma.ecom.helper.Helper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SMA-STORE: Search Result</title>
    <%@include file="components/common_css_js.jsp" %>
<body>
<%@include file="components/navbar.jsp" %>
<div class="row ml-2">
    <!-- //show products -->
    <div class="col-md-10">
        <!-- row -->
        <div class="row mt-4">
            <div class="col-md-12">
                <div class="card-columns">
                    <%
                        ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
                        for (Product product : products) {
                    %>
                    <div class="card">
                        <div class="container text-center">
                            <img alt="..." style="max-height:200px; max-width:100px; width:auto;"
                                 src="img/<%= product.getImg()%>" class="card-img-top m-2">
                        </div>
                        <div class="class-body">
                            <h5 class="card-title ml-2"><%=product.getName() %>
                            </h5>
                            <p class="card-text ml-2"><%=Helper.get10Words(product.getDescription()) %>
                            </p>
                        </div>
                        <div class="card-footer text-center">
                            <button class="btn custom-bg text-white"
                                    onclick="add_to_cart(<%=product.getId() %>,'<%=product.getName() %>','<%=product.getPrice() %>')">
                                Buy
                            </button>
                            <button class="btn btn-outline-success">
                                <%=product.getPrice()%> MAD
                            </button>
                        </div>

                    </div>
                    <%
                        }
                        if (products.isEmpty())
                            out.println("<h3>No items found</h3>");
                    %>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
