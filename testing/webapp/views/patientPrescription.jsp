<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prescription</title>
</head>
<body>
    <%@ include file="include/_nav.jsp" %>
    <br><br>
    <h3 align="center">Please follow this Prescription</h3>

    <table border="1px" align="center">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Medicine-1</th>
            <th>Medicine-2</th>
            <th>Medicine-3</th>
                        <th>Medicine-4</th>
                        <th>Medicine-5</th>
        </tr>
        <tr>
            <td>${sessionScope.DB_med_firstname}</td>
            <td>${sessionScope.DB_med_lastname}</td>
            <td>${sessionScope.DB_med_med1}</td>
            <td>${sessionScope.DB_med_med2}</td>
            <td>${sessionScope.DB_med_med3}</td>
            <td>${sessionScope.DB_med_med4}</td>
            <td>${sessionScope.DB_med_med5}</td>
        </tr>
    </table>
</body>
</html>