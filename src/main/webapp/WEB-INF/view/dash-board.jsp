<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
   <head>
   <link href="${pageContext.request.contextPath}/resource/home.css" rel="stylesheet" >
   <link rel="icon" href="${pageContext.request.contextPath}/resource/favicon.ico" type="image/gif" sizes="16x16">
   </head>
   <body>
      <div class="home">
         <div class="items">
            <div class="grid-container">
               <c:forEach var="item" items="${items}">
                  <div class="card">
                     <div class="img"></div>
                     <div class="itemCon">
                        <div class="itemConTxt">
                           <div>Name: ${item.itemName}</div>
                           <div>Price: ${item.itemPrice}</div>
                        </div>
                        <div class="ItemConBut">
                           <button onclick="location.href='addItem?itemId=${item.itemId}'" type="button" class="inputButton">Add to Cart</button>
                        </div>
                     </div>
                  </div>
               </c:forEach>
            </div>
         </div>
         <div class="cart">
            <div style="display: flex; justify-content: space-between; width: 100%">
               <button onclick="location.href='/'" type="button" class="normalInputButton">Back to Homepage</button>
               <button onclick="location.href='showOrders'" type="button" class="normalInputButton">Show Your Orders</button>
            </div>
            <h2>Cart</h2>
            <div class="cartItems">
               <c:forEach var="item" items="${cart.orderItems}">
                  <div class="card">
                  <div class="itemCon">
                     <div class="imgCart"></div>

                        <div class="itemConText">
                           <div>Name: ${item.item.itemName}</div>
                           <div>Price: ${item.item.itemPrice}</div>
                           <div>Quantity: ${item.quantity}</div>
                        </div>
                        <div class="itemConBut">
                           <div class="plusMinus">
                              <button onclick="location.href='incrementItem?itemId=${item.item.itemId}'" type="button"> + </button>
                              <button onclick="location.href='decrementItem?itemId=${item.item.itemId}'" type="button"> - </button>
                           </div>
                           <div>
                              <button onclick="location.href='deleteItem?itemId=${item.item.itemId}'" type="button" class="redInputButton">Remove</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </c:forEach>
            </div>
            <br>
            <div>Total price: ${cart.totalPrice}</div>
            <br>
            <button onclick="location.href='placeOrder'" type="button" class="inputButton">Place Order</button>
         </div>
      </div>
   </body>
</html>