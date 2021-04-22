<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
   <head>

   <link rel="icon" href="${pageContext.request.contextPath}/resource/favicon.ico" type="image/gif" sizes="16x16">
   <style>
   .body{
               margin: 0;
               overflow: hidden;
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
               }
               .home{
               height: 100vh; overflow: hidden;display: flex;flex-direction: row;
               }
               .card:hover {
               box-shadow: 0 16px 32px 0 rgba(0,0,0,0.2);
               }
               .grid-container {
               display: grid;
               grid-template-columns: auto auto auto;
               padding: 10px;
               }
               .items{
               background: white;
               height: 100%;
               width: 100%;
               overflow-y: auto;
               overflow-x: hidden;
               }
               .items::-webkit-scrollbar {
               display: none;
               }
               .cart{
               font-weight: bold;
               width: 30vw;
               background: lightgrey;
               height: 100%;
               justify-content: center;
               align-items: center;
               display: flex;
               flex-direction: column;
               padding: 20px;
               }
               .cartItems{
               font-weight: normal;
               color: black;
               -moz-box-shadow:    inset 0 0 8px #000000;
               -webkit-box-shadow: inset 0 0 8px #000000;
               box-shadow:         inset 0 0 8px #000000;
               width: 100%;
               height: 75%;
               overflow-y: auto;
               overflow-x: hidden;
               background: white;
               border-radius: 10px;
               }
               .cartItems::-webkit-scrollbar {
               display: none;
               }
               .button{
               padding: 20px 50px;
               background-color: orange;
               }
               .inputButton {
               text-align: center;
               transition: 0.25s;
               width: 150px;
               padding: 10px;
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
               .img{
               height: 13vw;
               width: 100%;
               background: lightgrey;
               border-radius: 20px;
               margin-bottom: 20px;
               }
               .imgCart{
                        height: 5vw;
                        width: 5vw;
                        background: lightgrey;
                        border-radius: 20px;
                        }
               .itemCon{
               display: flex;
               justify-content: space-between;
               }
               .plusMinus{
               display: flex;
               justify-content: space-between;
               }
               .itemConBut{
               display: flex;
               height: 5vw;
               flex-direction: column;
               justify-content: space-between;
               }
               .itemConTxt{
               text-align: left;
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
                                           .normalInputButton {
                                                                                   text-align: center;
                                                                                   transition: 0.25s;
                                                                                   padding: 5px 10px;
                                                                                   outline: none;
                                                                                   border-radius: 24px;
                                                                                   border: 1px solid #436576;
                                                                                   background: #436576;
                                                                                   color: white;
                                                                                   font-weight: bold;
                                                                                   }
                                                                                   .normalInputButton:hover {
                                                                                   background: white;
                                                                                   color: #436576;
                                                                                   cursor: pointer;
                                                                                   }
   </style>
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