<%--
  Created by IntelliJ IDEA.
  User: ah-ma
  Date: 12/04/2024
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-dark custom-bg">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">SMA-STORE</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <form id="search" action="SearchServlet" method="post" class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" id="product" name="product" placeholder="Search" aria-label="Search" style="width: 700px; margin-left: 100px;">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
