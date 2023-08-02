<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>

</head>
<body>
<%@ include file="include/nav.jsp" %>

    <h1 align="center">Change Password</h1>
    <br>
    <div style="display:flex;justify-content:center;">
        <form action="" method="post">

            <label for="currentpassword">Current Password</label>
            <br>
            <input type="password" name="currentpassword" id="currentpassword" value=${requestScope.currentpassword}>
            ${requestScope.currentpasswordErrMsg}
            <c:out value="${requestScope.currentpasswordErrMsg ? requestScope.currentpasswordErrMsg : ''}" />
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



            <input type="submit" value="Update">

            ${requestScope.successMsg}
            <c:out value="${requestScope.successMsg ? requestScope.successMsg : ''}" />
            ${requestScope.errMsg}
            <c:out value="${requestScope.errMsg ? requestScope.errMsg : ''}" />
        </form>
    </div>
</body>
</html>