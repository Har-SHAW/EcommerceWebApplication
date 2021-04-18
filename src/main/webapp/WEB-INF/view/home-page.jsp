<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
<button onclick="location.href='dashboard'" type="button">User Dashboard</button>
<br>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<button onclick="location.href='adminDashboard'" type="button">Admin Dashboard</button>
<br>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MANAGER')">
<button onclick="location.href='managerDashboard'" type="button">Manager Dashboard</button>
<br>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_EMPLOYEE')">
<button onclick="location.href='employeeDashboard'" type="button">Employee Dashboard</button>
<br>
<br>
</sec:authorize>

<button onclick="location.href='logout'" style="background-color: red; color: white; font-weight: bold" type="button">Logout</button>
</body>
</html>