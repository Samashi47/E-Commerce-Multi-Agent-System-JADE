<%--
  Created by IntelliJ IDEA.
  User: ah-ma
  Date: 14/04/2024
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.sma.ecom.helper.Helper" %>
<%@page import="com.sma.ecom.entities.ProductDAO" %>
<%@page import="com.sma.ecom.helper.FactoryProvider" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sma.ecom.entities.Product" %>
<html>
<head>
    <title>SMA-STORE: Home</title>
    <%@include file="components/common_css_js.jsp" %>
    <style>
        body {
            background-color: #aa9cd5; /* light purple */
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>SMA-STORE</h1>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <form class="input-group mb-3" id="search" action="SearchServlet" method="post">
                <input type="text" id="product" name="product" class="form-control form-control-lg" placeholder="Search for products" aria-label="Search for products" aria-describedby="button-addon2">
                <button class="btn btn-outline-primary" type="submit" id="button-addon2">Search</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

