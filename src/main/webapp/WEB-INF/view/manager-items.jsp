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
<br>
<br>
<br>
<br>
<c:forEach var="item" items="${items}">
    <div>Name: ${item.itemName} | Price: ${item.itemPrice} | <button onclick="location.href='editItem?itemId=${item.itemId}'" type="button">EDIT</button></div>
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
<button onclick="location.href='/managerDashboard'" type="button"><-- Back</button>
</div>
</body>
</html>