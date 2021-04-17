<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
<a href="dashboard">go to dash board</a>
<br>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="adminDashboard">go to admin dash board</a>
<br>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MANAGER')">
<a href="managerDashboard">manager dash board</a>
<br>
<br>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_EMPLOYEE')">
<a href="employeeDashboard">employee dash board</a>
<br>
<br>
</sec:authorize>

<a href="logout">logout</a>
</body>
</html>