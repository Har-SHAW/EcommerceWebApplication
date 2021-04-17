<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
u have user rights
<c:forEach var="item" items="${items}">
    <div>${item.itemName}  ${item.itemPrice}  <a href="editItem?itemId=${item.itemId}">EDIT</a> </div>
    <br>
</c:forEach>
<br>
<br>
ADD ITEM:
<form:form action="processAdd" modelAttribute="item" cssClass="formStyle">
        <form:input path="itemId" type="hidden"/>
        <form:input path="itemName" placeholder="Item Name" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="itemName" cssClass="error" />
        <br>
        <form:input path="itemPrice" type="Price" placeholder="Price" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="itemPrice" cssClass="error" />
        <br>
        <br>
        <input type="submit" class="inputButton"/>
        </form:form>
<br>
<br>
<a href="/managerDashboard">back to homepage</a>
</body>
</html>