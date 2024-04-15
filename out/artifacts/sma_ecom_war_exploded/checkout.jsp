<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Mycart : Checkout</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<script>
    function redirectToBuyServlet() {
        // Redirect to the servlet URL
        window.location.href = "BuyServlet";
    }

    function redirectToIndexServlet() {
        // Redirect to the servlet URL
        window.location = "index.jsp";
    }
</script>
<%@include file="components/navbar.jsp" %>
<div class="container">
    <div class="row mt-5">
        <div class="col-md-6">
            <!-- card -->

            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-5">Your selected items</h3>
                    <div class="cart-body"></div>
                </div>
            </div>


        </div>

        <div class="col-md-6">
            <!-- form details -->
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-5">Your details for order</h3>
                    <form>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" id="exampleInputEmail1"
                                   aria-describedby="emailHelp" placeholder="Enter email">
                            <small id="emailHelp" class="form-text text-muted">We'll
                                never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Your name</label>
                            <input type="text" class="form-control" id="name"
                                   aria-describedby="emailHelp" placeholder="Enter your name">

                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">Your shipping address</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1"
                                      rows="3" placeholder="Enter your address"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Your Contact Number</label>
                            <input type="text" class="form-control" id="number"
                                   aria-describedby="emailHelp" placeholder="Enter your number">
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success" type="button" onclick="sendTableDataToServlet()">
                                Order now
                            </button>
                            <button class="btn btn-outline-primary" type="button" onclick="redirectToIndexServlet()">
                                Continue shopping
                            </button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
