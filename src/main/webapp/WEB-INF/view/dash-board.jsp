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
    <a href="incrItem?itemId=${item.item.itemId}">+</a>
    <a href="decrItem?itemId=${item.item.itemId}">-</a>
    <a href="deleteItem?itemId=${item.item.itemId}">delete</a>
    </div>
    <br>
</c:forEach>
<br>
<br>
<a href="placeOrder">place order</a>
</body>
</html>