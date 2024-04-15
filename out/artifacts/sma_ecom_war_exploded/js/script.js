
function add_to_cart(pid, pname, price) {
    console.log('add_to_cart function called with pid:', pid, 'pname:', pname, 'price:', price);
    let cart = localStorage.getItem("cart");
    if (cart == null) {
        //no  cart yet
        let products = [];
        let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price}
        products.push(product);
        localStorage.setItem("cart", JSON.stringify(products));
        console.log("Product is added for the first time")
    } else {
        //cart is already present
        let pcart = JSON.parse(cart);

        let oldProduct = pcart.find((item) => item.productId === pid)
        console.log(oldProduct)
        if (oldProduct) {
            //we have to increase the quuantity
            oldProduct.productQuantity = oldProduct.productQuantity + 1
            pcart.map((item) => {
                if (item.productId === oldProduct.productId) {
                    item.productQuantity = oldProduct.productQuantity;
                }
            })
            localStorage.setItem("cart", JSON.stringify(pcart));
        } else {
            //we have add product
            let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price}
            pcart.push(product)
            localStorage.setItem("cart", JSON.stringify(pcart));
            console.log("Product id added")
        }
    }
    updateCart();
    console.log('Redirecting to checkout.jsp');
    console.log('Calling gotoCheckout function');
    gotoCheckout();
}
function gotoCheckout() {
    console.log('gotoCheckout function called');
    window.location = "checkout.jsp"
}
function updateCart() {
    let cartString = localStorage.getItem("cart");
    let cart = JSON.parse(cartString);
    if (cart == null || cart.length === 0) {
        console.log("Cart is empty")
        $(".cart-items").html("( 0 )");
        $(".cart-body").html("<h3>Cart does not have any items </h3>")
        $(".checkout-btn").attr('disabled', true);
    } else {
        //there is something
        console.log(cart);
        $(".cart-items").html(`( ${cart.length} )`);
        let table = `
		<table class='table'>
		<thead class='thead-light'>
		<tr>
		<th>Item Name </th>
		<th>Price </th>
		<th>Quantity </th>
		<th>Total Price</th>
		<th>Action </th>
		</tr>
		</thead>
		`;
        let totalPrice = 0;
        cart.map((item) => {
            table += `
				<tr>
				<td>${item.productName}</td>
				<td>${item.productPrice} MAD</td>
				<td>${item.productQuantity}</td>
				<td>${item.productQuantity * item.productPrice}</td>
				<td> <button onclick="deleteItemFromCart(${item.productId})" class="btn btn-danger btn-sm">Remove</button></td>
				</tr>
			`
            totalPrice += item.productPrice * item.productQuantity;
        })
        table = table + `
		<tr><td colspan='5' class='text-right font-weight-bold m-5'> Total Price: ${totalPrice} MAD</td></tr>
		</table>`
        $(".cart-body").html(table)
        $(".checkout-btn").attr('disabled', false);
        console.log("removed")
    }
}
function deleteItemFromCart(pid) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    let newcart = cart.filter((item) => item.productId !== pid);

    localStorage.setItem('cart', JSON.stringify(newcart))
    updateCart();
}
$(document).ready(function () {
    updateCart();
})
function sendTableDataToServlet() {
    // Select the table
    var table = $(".cart-body").find('table');

    // Initialize an array to hold the data
    var data = [];

    // Get the first row of the table after the header
    var $firstRow = table.find('tr').eq(1);

    // Get the cells of the row
    var $tds = $firstRow.find('td');

    // Create an object to hold the data of the row
    var row = {
        productName: $tds.eq(0).text()
    };

    // Add the object to the array
    data.push(row);

    // Convert the array to a JSON string
    var json = JSON.stringify(data);

    // Send the JSON string to the servlet using AJAX
    $.ajax({
        url: 'BuyServlet',  // Replace with the URL of your servlet
        type: 'POST',
        data: json,
        contentType: 'application/json',
        success: function (response) {
            // Handle the response from the servlet
            console.log(response);
        },
        error: function (error) {
            // Handle errors
            console.log(error);
        }
    });
    if (!row.productName || row.productName.trim() === '') {
        window.location = "error.jsp";
    }
    else
        window.location = "complete.jsp";
}