<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<sec:authorize access="hasRole('ROLE_USER')">
<button onclick="location.href='dashboard'" type="button" class="inputButton">User Dashboard</button>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<button onclick="location.href='adminDashboard'" type="button" class="inputButton">Admin Dashboard</button>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MANAGER')">
<button onclick="location.href='managerDashboard'" type="button" class="inputButton">Manager Dashboard</button>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_EMPLOYEE')">
<button onclick="location.href='employeeDashboard'" type="button" class="inputButton">Employee Dashboard</button>
<br>
</sec:authorize>
<br>
<button onclick="location.href='logout'" style="background-color: red; color: white; font-weight: bold" type="button">Logout</button>
</div>
</body>
</html>