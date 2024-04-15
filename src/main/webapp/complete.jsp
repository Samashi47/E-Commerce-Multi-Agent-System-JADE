<%--
  Created by IntelliJ IDEA.
  User: ah-ma
  Date: 13/04/2024
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Purchase Complete</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        function redirectToIndexServlet() {
            // Redirect to the servlet URL
            window.location.href = "index.jsp";
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-success text-white">
                    <h5 class="mb-0">Product Bought Successfully</h5>
                </div>
                <div class="justify-content-center card-body">
                    <p class="card-text">Thank you for your purchase! Your product has been successfully bought.</p>
                    <button onclick="redirectToIndexServlet()" class="btn btn-outline-primary">Back to Home</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
