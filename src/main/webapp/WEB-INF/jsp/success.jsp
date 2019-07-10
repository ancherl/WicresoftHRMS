<%--
  Created by IntelliJ IDEA.
  User: daixin
  Date: 2018/9/23
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Success Page</title>
</head>
<body>
<table border="1">
    <tr>
        <td>EmployeeName</td>
        <td>Email</td>
        <td>Department</td>
        <td>Province</td>
        <td>City</td>
    </tr>
    <c:choose>
        <c:when test="${ not empty empList}">
            <c:forEach items="${empList}" var="emp">
                <tr>
                    <td>${emp.employeeName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.department}</td>
                    <td>${emp.address.province}</td>
                    <td>${emp.address.city}</td>
                </tr>
            </c:forEach>
        </c:when>
    </c:choose>
</table>

</body>
</html>
