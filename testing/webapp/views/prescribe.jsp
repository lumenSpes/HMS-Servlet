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

            <h1 align="center">Prescribe</h1>
    <div style="display:flex;justify-content:center;">

            <form action="" method="post">
                <label for="firstname">First Name</label>
                <br>
                <input type="text" name="firstname" id="firstname" value=${requestScope.firstname}>

                <br><br>
                <label for="lastname">Last Name</label>
                <br>
                <input type="text" name="lastname" id="lastname" value=${requestScope.lastname}>
                ${requestScope.lastnameErrMsg}
                <c:out value="${requestScope.lastnameErrMsg ? requestScope.lastnameErrMsg : ''}" />
                <br><br>
                <label for="username">User Name</label>
                <br>
                <input type="text" name="username" id="username" value=${requestScope.username}>
                ${requestScope.usernameErrMsg}
                <c:out value="${requestScope.usernameErrMsg ? requestScope.usernameErrMsg : ''}" />
                <br><br>
                <label for="med1">Medicine-1</label>
                <br>
                <input type="text" name="med1" id="med1" value=${requestScope.med1}>
                ${requestScope.med1ErrMsg}
                <c:out value="${requestScope.med1ErrMsg ? requestScope.med1ErrMsg : ''}" />
                <br><br>

                <label for="med2">Medicine-2</label>
                <br>
                <input type="text" name="med2" id="med2" value=${requestScope.med2}>
                ${requestScope.med2ErrMsg}
                <c:out value="${requestScope.med2ErrMsg ? requestScope.med2ErrMsg : ''}" />
                <br><br>

                <label for="med3">Medicine-3</label>
                <br>
                <input type="text" name="med3" id="med3" value=${requestScope.med3}>
                ${requestScope.med3ErrMsg}
                <c:out value="${requestScope.med3ErrMsg ? requestScope.med3ErrMsg : ''}" />
                <br><br>

                <label for="med4">Medicine-4</label>
                <br>
                <input type="text" name="med4" id="med4" value=${requestScope.med4}>
                ${requestScope.med4ErrMsg}
                <c:out value="${requestScope.med4ErrMsg ? requestScope.med4ErrMsg : ''}" />
                <br><br>

                <label for="med5">Medicine</label>
                <br>
                <input type="text" name="med5" id="med5" value=${requestScope.med5}>
                ${requestScope.med5ErrMsg}
                                <c:out value="${requestScope.med5ErrMsg ? requestScope.med5ErrMsg : ''}" />
                <br><br>

                <input type="submit" value="Prescribe">

                ${requestScope.successMsg}
                <c:out value="${requestScope.successMsg ? requestScope.successMsg : ''}" />
                ${requestScope.errMsg}
                <c:out value="${requestScope.errMsg ? requestScope.errMsg : ''}" />
            </form>
        </div>
</body>
</html>