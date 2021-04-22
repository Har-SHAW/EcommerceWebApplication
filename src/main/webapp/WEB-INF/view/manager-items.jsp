<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="${pageContext.request.contextPath}/resource/favicon.ico" type="image/gif" sizes="16x16">
<style>
::placeholder{
  color: white;
    font-weight: normal;
  }

.inputStyle {
    text-align: center;
    transition: 0.5s;
    width: 225px;
    background: lightgrey;
    padding: 14px 10px;
    outline: none;
    border-radius: 24px;
    border: 1px solid grey;
    color: black;
    font-weight: bold;
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

.inputStyle:focus {
    width: 50vw;
    text-align: center;
    width: 350px;
    background: white;
    color: black;
}

.formStyle {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
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
            display: grid;
            grid-template-columns: auto auto auto auto;
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
            .imgCart{
                                 height: 6vw;
                                 width: 100%;
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
                                     .error{
                                     color: red;
                                     }
</style>
</head>
<body>
<div class="home">
<br>
<br>
<br>
<br>
<div class="grid-container">

<c:forEach var="item" items="${items}">
<div class="card">
<div class="itemCon">
<div class="imgCart"></div>
<div style="width: 100%; padding: 10px">
<div>Name: </div>
<div>Price: </div>
</div>
<div style="width: 100%; padding: 10px">
<div style="display: flex; flex-direction: column; justify-content: space-between; height: 100%">
<div>
    <div>${item.itemName}</div>
    <div>${item.itemPrice}</div>
    </div>
    <button onclick="location.href='editItem?itemId=${item.itemId}'" style="width: 100%" type="button">EDIT</button>
    <c:if test="${item.isOutOfStock == true}">
        <button onclick="location.href='inStock?itemId=${item.itemId}'" style="width: 100%" type="button">Set in Stock</button>
    </c:if>
    <c:if test="${item.isOutOfStock == false}">
        <button onclick="location.href='noStock?itemId=${item.itemId}'" style="width: 100%" type="button">Set no Stock</button>
    </c:if>
    </div>
    </div>
    </div>
    </div>
</c:forEach>

</div>
<br>
<br>
ADD ITEM:
<br>
<br>
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
<button onclick="location.href='/managerDashboard'" class="redInputButton" type="button">Back</button>
</div>
</body>
</html>