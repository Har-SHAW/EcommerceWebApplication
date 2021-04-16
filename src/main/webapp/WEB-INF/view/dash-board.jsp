<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
u have user rights

<c:forEach var="item" items="${items}">
    <div>${item.itemName}  ${item.itemPrice}  <form:form action="addItem?itemId=${item.itemId}"><input type="submit" value="add"/></form:form> </div>
    <br>
</c:forEach>
<br>
<br>
Cart:
<br>
<c:forEach var="item" items="${cart.orderItems}">
    <div>${item.item.itemName}  ${item.item.itemPrice}  ${item.quantity}</div>
    <br>
</c:forEach>
</body>
</html>