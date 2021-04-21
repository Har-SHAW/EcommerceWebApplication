<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<link rel="icon" href="${pageContext.request.contextPath}/resource/favicon.ico" type="image/gif" sizes="16x16">
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
         .redInputButton {
                            text-align: center;
                            transition: 0.25s;
                            padding: 5px 10px;
                            outline: none;
                            border-radius: 24px;
                            border: 1px solid #D2042D;
                            background: #D2042D;
                            color: white;
                            font-weight: bold;
                            }
                            .redInputButton:hover {
                            background: white;
                            color: #D2042D;
                            cursor: pointer;
                            }
                            .card {
                                        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
                                        transition: 0.3s;
                                        width: 18vw;
                                        margin: 20px;
                                        border-radius: 20px;
                                        padding: 20px;
                                        display: flex;
                                        flex-direction: column;
                                        background-color: white;
                                        }
                                        .card:hover {
                                        box-shadow: 0 16px 32px 0 rgba(0,0,0,0.2);
                                        }
                                        .grid-container {
                                        display: inline-grid;
                                        grid-template-columns: auto auto auto;
                                        padding: 10px;
                                        }.itemCon{
                                                                 display: flex;
                                                                 justify-content: space-between;
                                                                 }
</style>
</head>
<body>
<div class="home"
<br>
<br>
<div>Orders:</div>
<br>
<div class="grid-container">
<c:forEach var="order" items="${orders}">
<div class="card">
<div class="itemCon">
<div>
<div>Date: </div>
<div>Items: </div>
</div>

<div>
<div>${order.orderDate}</div>
<div>${order.user.phoneNo}</div>
<select>
<c:forEach var="item" items="${order.orderItems}">
    <option>Item: ${item.item.itemName}  |  Price: ${item.item.itemPrice} | Quantity: ${item.quantity}</option>
</c:forEach>
</select>
</div>
</div>
</div>
</c:forEach>
</div>
<button onclick="location.href='/dashboard'" type="button" class="redInputButton">Back</button>
</div>
</body>
</html>