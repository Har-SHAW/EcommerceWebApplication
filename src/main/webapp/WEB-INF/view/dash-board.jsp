<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
u have user rights

<c:forEach var="item" items="${items}">
    <div>${item.itemName}  ${item.itemPrice}
    <a href="addItem?itemId=${item.itemId}">addItem</a>
    </div>
    <br>
</c:forEach>
<br>
<br>
Cart:
<br>
<c:forEach var="item" items="${cart.orderItems}">
    <div>${item.item.itemName}  ${item.item.itemPrice}  ${item.quantity}
    <a href="incrementItem?itemId=${item.item.itemId}">+</a>
    <a href="decrementItem?itemId=${item.item.itemId}">-</a>
    <a href="deleteItem?itemId=${item.item.itemId}">delete</a>
    </div>
    <br>
</c:forEach>
<br>
<div>Total price: ${cart.totalPrice}</div>
<br>
<br>
<br>
<a href="placeOrder">place order</a>

<br>
<br>
<a href="showOrders">show your order</a>

<br>
<br>
<a href="/">back to homepage</a>
</body>
</html>