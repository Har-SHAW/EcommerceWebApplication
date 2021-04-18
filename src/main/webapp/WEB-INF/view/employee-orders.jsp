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
<div>Username: ${order.user.username}</div>
<div>Email: ${order.user.email}</div>
<div>Phone Number: ${order.user.phoneNo}</div>
<div>Items: </div>
<c:forEach var="item" items="${order.orderItems}">
    <div>Item Name: ${item.item.itemName}  |  Price: ${item.item.itemPrice} | Quantity: ${item.quantity}</div>
</c:forEach>
<br>
<br>
</c:forEach>
<button onclick="location.href='/employeeDashboard'" type="button"><-- Back</button>
</body>
</html>