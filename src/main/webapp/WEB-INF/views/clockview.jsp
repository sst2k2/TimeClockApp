<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>

</head>
<body>
<h1>Current Time : <c:out value="${clock.hour.hour}:${clock.minute.minute}:${clock.second.second} ${clock.meridian.meridian}"></c:out></h1>
<form:form method="POST" action="/timeclock/app/clock/goBackInTime" modelAttribute="offset" >
    <table>
    <tr>
        <td><form:label path="offset">offset</form:label></td>
        <td><form:input path="offset" type="text"/></td>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>

    </tr>
    </table>
</form:form>
</body>
</html>
