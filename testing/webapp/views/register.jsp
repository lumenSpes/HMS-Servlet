<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>

</head>
<body>
    <h1 align="center">Register</h1>
    <br>
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

            <label for="password">Password</label>
            <br>
            <input type="password" name="password" id="password" value=${requestScope.password}>
            ${requestScope.passwordErrMsg}
                            <c:out value="${requestScope.passwordErrMsg ? requestScope.passwordErrMsg : ''}" />
            <br><br>

            <label for="confirmpassword">Confirm Password</label>
            <br>
            <input type="password" name="confirmpassword" id="confirmpassword" value=${requestScope.confirmpassword}>
            ${requestScope.confirmpasswordErrMsg}
                            <c:out value="${requestScope.confirmpasswordErrMsg ? requestScope.confirmpasswordErrMsg : ''}" />
            <br><br>

            <label for="age">Age</label>
            <br>
            <input type="text" name="age" id="age" value=${requestScope.age}>
            ${requestScope.ageErrMsg}
                            <c:out value="${requestScope.ageErrMsg ? requestScope.ageErrMsg : ''}" />
            <br><br>

            <label for="role">Role</label>
            <br>
            <input type="text" name="role" id="role" value=${requestScope.role}>
            ${requestScope.roleErrMsg}
            <c:out value="${requestScope.roleErrMsg ? requestScope.roleErrMsg : ''}" />
            <br><br>

            <input type="submit" value="Register">

            ${requestScope.successMsg}
            <c:out value="${requestScope.successMsg ? requestScope.successMsg : ''}" />
            ${requestScope.errMsg}
            <c:out value="${requestScope.errMsg ? requestScope.errMsg : ''}" />
        </form>
    </div>


</body>
</html>