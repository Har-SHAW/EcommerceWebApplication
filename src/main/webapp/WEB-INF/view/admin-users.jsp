<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
u have user rights
<c:forEach var="user" items="${users}">
    <div>${user.username}  ${user.email}
    <c:forEach var="role" items="${user.roles}">
        <div>${role.role}</div>
    </c:forEach>
    </div>
    <br>
</c:forEach>

<br>
<br>
<div>Change role of a user</div>
<form:form action="changeRole" modelAttribute="userRole" cssClass="formStyle">
<form:input path="username" placeholder="Username" cssClass="inputStyle" autocomplete="off"/>
<form:errors path="username" cssClass="error" />
<br>
<form:input path="role" placeholder="Role" cssClass="inputStyle" autocomplete="off"/>
<form:errors path="role" cssClass="error" />
<br>
Add <form:radiobutton path="action" value="Add"/>
Remove <form:radiobutton path="action" value="Remove"/>
<form:errors path="action" cssClass="error" />
<br>
<input type="submit"/>
</form:form>
<br>
<br>
<br>
<a href="/">back to homepage</a>
</body>
</html>