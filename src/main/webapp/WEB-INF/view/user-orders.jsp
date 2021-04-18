<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
<br>
<br>
<div>Orders:</div>
<br>
<c:forEach var="order" items="${orders}">
<div>Date: ${order.orderDate}</div>
<div>Items: </div>
<c:forEach var="item" items="${order.orderItems}">
    <div>itemName: ${item.item.itemName} |  price: ${item.item.itemPrice} | quantity: ${item.quantity}</div>
</c:forEach>
<br>
<br>
</c:forEach>
<button onclick="location.href='dashboard'" type="button"><-- Back</button>
</body>
</html>