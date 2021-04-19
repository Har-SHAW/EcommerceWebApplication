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
<div class="home"
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
<button onclick="location.href='/employeeDashboard'" type="button" style="background-color: red; color: white; font-weight: bold;"><-- Back</button>
</div>
</body>
</html>