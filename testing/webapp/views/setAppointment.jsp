<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<%@ include file="include/nav.jsp" %>

    <h3 align="center">Set Appointment</h3>

    <div style="display:flex;justify-content:center;">
            <form action="" method="post">
                <label for="firstname">First Name</label>
                <br>
                <input type="text" name="firstname" id="firstname" value=${requestScope.firstname}>
                ${requestScope.firstnameErrMsg}
                                <c:out value="${requestScope.firstnameErrMsg ? requestScope.firstnameErrMsg : ''}" />
                <br><br>

                <label for="lastname">Last Name</label>
                <br>
                <input type="text" name="lastname" id="lastname" value=${requestScope.lastname}>
                ${requestScope.lastnameErrMsg}
                                <c:out value="${requestScope.lastnameErrMsg ? requestScope.lastnameErrMsg : ''}" />
                <br><br>

                <label for="email">Email</label>
                <br>
                <input type="email" name="email" id="email" value=${requestScope.email}>
                ${requestScope.emailErrMsg}
                                <c:out value="${requestScope.emailErrMsg ? requestScope.emailErrMsg : ''}" />
                <br><br>

                <label for="username">User Name</label>
                <br>
                <input type="text" name="username" id="username" value=${requestScope.username}>
                ${requestScope.usernameErrMsg}
                                <c:out value="${requestScope.usernameErrMsg ? requestScope.usernameErrMsg : ''}" />
                <br><br>

                <label for="date">Date</label>
                <br>
                <input type="text" name="date" id="date" value=${requestScope.date}>
                ${requestScope.dateErrMsg}
                                <c:out value="${requestScope.dateErrMsg ? requestScope.dateErrMsg : ''}" />
                <br><br>

                <label for="assignedTo">Assigned To</label>
                <br>
                <input type="text" name="assignedTo" id="assignedTo" value=${requestScope.assignedTo}>
                ${requestScope.assignedToErrMsg}
                                <c:out value="${requestScope.assignedToErrMsg ? requestScope.assignedToErrMsg : ''}" />
                <br><br>

                <input type="submit" value="Add Appointment">

                ${requestScope.successMsg}
                <c:out value="${requestScope.successMsg ? requestScope.successMsg : ''}" />
                ${requestScope.errMsg}
                <c:out value="${requestScope.errMsg ? requestScope.errMsg : ''}" />
            </form>
        </div>
</body>
</html>