<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

</head>
<body>
    <h1 align="center">Login</h1>
    <div style="display:flex;justify-content:center;">

        <form action="" method="post">
            <div style="align-items:left;">
                <br>
                <label for="username">Username</label>
                <br>
                <input type="text" name="username" id="username" value=${requestScope.username}>

                ${requestScope.usernameErrMsg}
                <c:out value="${requestScope.usernameErrMsg ? requestScope.usernameErrMsg : ''}" />
            </div>

            <br><br>
            <div style="align-items:left;">
                <label for="password">Password</label>
                <br>
                <input type="password" name="password" id="password" value=${requestScope.password}>

                ${requestScope.passwordErrMsg}
                <c:out value="${requestScope.passwordErrMsg ? requestScope.passwordErrMsg : ''}" />
            </div>
            <br><br>
            <input type="submit" value="Login">

            <br><br>
            ${requestScope.errMsg}
            <c:out value="${requestScope.errMsg ? requestScope.errMsg : ''}" />
        </form>
    </div>
</body>
</html>