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
<div>${order.orderDate}</div>
<div>${order.user.username}</div>
<div>${order.user.email}</div>
<div>${order.user.phoneNo}</div>
<br>
<c:forEach var="item" items="${order.orderItems}">
    <div>itemName: ${item.item.itemName}  price: ${item.item.itemPrice}  quantity: ${item.quantity}</div>
    <br>
</c:forEach>
<br>
<br>
</c:forEach>
<br>
<br>
<a href="/employeeDashboard">back to dashboard</a>
</body>
</html>