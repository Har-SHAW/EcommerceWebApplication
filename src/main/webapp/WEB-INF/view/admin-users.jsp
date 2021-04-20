<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="${pageContext.request.contextPath}/resource/favicon.ico" type="image/gif" sizes="16x16">
<style>
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
         .error{
         color: red;
         }
         .form{
         text-align: center;
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

          .inputStyle:focus {
              width: 50vw;
              text-align: center;
              width: 350px;
              background: white;
              color: black;
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
</style>
</head>
<body>
<div class="home">
<br><br><br><br>
<div class="grid-container">
<c:forEach var="user" items="${users}">
<div class="card">
<div class="itemCon">
<div class="imgCart"></div>
<div>
<div>Username: </div>
<div>Email: </div>
<div>Roles: </div>
</div>
<div>
    <div>${user.username}</div>
    <div>${user.email}</div>
    <div>
    <select>
    <c:forEach var="role" items="${user.roles}">
        <option>${role.role}</option>
    </c:forEach>
    </select>
    <br>
    <br>
    <button onclick="location.href='deleteUser?username=${user.username}'" type="button" class="redInputButton">DELETE USER</button>
    </div>
    </div>
    </div>
    </div>
</c:forEach>
</div>

<br>
<br>
<div>Change role of a user</div>
<br>
<form:form action="changeRole" modelAttribute="userRole" cssClass="form">
<form:input path="username" placeholder="Username" cssClass="inputStyle" autocomplete="off"/>
<br>
<form:errors path="username" cssClass="error" />
<br>
<br>
<form:input path="role" placeholder="Role" cssClass="inputStyle" autocomplete="off"/>
<br>
<form:errors path="role" cssClass="error" />
<br>
<br>
Add <form:radiobutton path="action" value="Add"/>
Remove <form:radiobutton path="action" value="Remove"/>
<br>
<form:errors path="action" cssClass="error" />
<br>
<br>
<br>
<input type="submit" class="inputButton"/>
</form:form>
<br>
<button onclick="location.href='/'" type="button" class="redInputButton">Back</button>
</div>
</body>
</html>