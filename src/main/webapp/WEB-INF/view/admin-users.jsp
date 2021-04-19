<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<style>
.body{
margin: 0px;
}
.home{
height: 100%;
width: 100%;
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
text-align: center;
}
.inputButton {
         text-align: center;
         transition: 0.25s;
         padding: 10px 20px;
         outline: none;
         border-radius: 24px;
         border: 1px solid #436576;
         background: #436576;
         color: white;
         font-weight: bold;
         }
         .inputButton:hover {
         background: white;
         color: #436576;
         cursor: pointer;
         }
</style>
</head>
<body>
<div class="home">
<br><br><br><br>
<c:forEach var="user" items="${users}">
    <div>Username: ${user.username}</div>
    <div>Email: ${user.email}</div>
    <div>
    <div>Roles: </div>
    <c:forEach var="role" items="${user.roles}">
        <div>${role.role}</div>
    </c:forEach>
    <button onclick="location.href='deleteUser?username=${user.username}'" type="button" style="background-color: red; color: white; font-weight: bold;">DELETE USER</button>
    </div>
    <br>
</c:forEach>
<br>
<br>
<div>Change role of a user</div>
<form:form action="changeRole" modelAttribute="userRole" cssClass="formStyle">
<form:input path="username" placeholder="Username" cssClass="inputStyle" autocomplete="off"/>
<br>
<form:errors path="username" cssClass="error" />
<br>
<form:input path="role" placeholder="Role" cssClass="inputStyle" autocomplete="off"/>
<br>
<form:errors path="role" cssClass="error" />
<br>
Add <form:radiobutton path="action" value="Add"/>
Remove <form:radiobutton path="action" value="Remove"/>
<br>
<form:errors path="action" cssClass="error" />
<br>
<br>
<input type="submit" class="inputButton"/>
</form:form>
<br>
<button onclick="location.href='/'" type="button" style="background-color: red; color: white; font-weight: bold;"><-- Back</button>
</div>
</body>
</html>