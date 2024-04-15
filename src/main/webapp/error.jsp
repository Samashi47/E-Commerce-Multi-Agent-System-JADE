<%--
  Created by IntelliJ IDEA.
  User: ah-ma
  Date: 14/04/2024
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Purchase Status</title>
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
                <div class="card-header bg-warning text-dark">
                    <h5 class="mb-0">Purchase Not Completed</h5>
                </div>
                <div class="justify-content-center card-body">
                    <p class="card-text text-danger">There was an error processing your purchase. Please try again later or contact us for assistance.</p>
                    <button onclick="redirectToIndexServlet()" class="btn btn-outline-primary">Back to Home</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

