<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Dashboard</title>
</head>
<body>
    <%@ include file="include/_nav.jsp" %>
    <h1 align="center">Welcome to patient dashboard.</h1>
    <br><br>
    <h3 align="center">Scheduled Appointments</h3>

    <table border="1px" align="center">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Assigned To</th>
            <th>Date</th>
        </tr>
        <tr>
            <td>${sessionScope.DB_patient_firstname}</td>
            <td>${sessionScope.DB_patient_lastname}</td>
            <td>${sessionScope.DB_patient_assignedTo}</td>
            <td>${sessionScope.DB_patient_date}</td>
        </tr>
    </table>
</body>
</html>