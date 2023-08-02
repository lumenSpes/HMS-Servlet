<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>

</head>
<body>
<%@ include file="include/nav.jsp" %>

    <h1 align="center">Profile</h1>
    <br>
    <div style="display:flex;justify-content:center;">
    <div>
        <form action="" method="post">
            <label for="firstname">First Name</label>
            <br>
            <input type="text" name="firstname" id="firstname" value=${sessionScope.DBfirstname}>

<br><br>
            <label for="lastname">Last Name</label>
            <br>
            <input type="text" name="lastname" id="lastname" value=${sessionScope.DBlastname}>
            ${requestScope.lastnameErrMsg}
            <c:out value="${requestScope.lastnameErrMsg ? requestScope.lastnameErrMsg : ''}" />
            <br><br>

            <label for="email">Email</label>
            <br>
            <input type="email" name="email" id="email" value=${sessionScope.DBemail}>
            ${requestScope.emailErrMsg}
            <c:out value="${requestScope.emailErrMsg ? requestScope.emailErrMsg : ''}" />
            <br><br>

            <label for="username">User Name</label>
            <br>
            <input type="text" name="username" id="username" value=${sessionScope.DBuser_username}>
            ${requestScope.usernameErrMsg}
            <c:out value="${requestScope.usernameErrMsg ? requestScope.usernameErrMsg : ''}" />
            <br><br>



            <label for="age">Age</label>
            <br>
            <input type="text" name="age" id="age" value=${sessionScope.DBage}>
            ${requestScope.ageErrMsg}
            <c:out value="${requestScope.ageErrMsg ? requestScope.ageErrMsg : ''}" />
            <br><br>

            <label for="role">Role</label>
                        <br>
                        <input type="text" name="role" id="role" value=${sessionScope.DBrole}>
                        ${requestScope.roleErrMsg}
                        <c:out value="${requestScope.roleErrMsg ? requestScope.roleErrMsg : ''}" />
                        <br><br>


<input type="submit" value="Edit">
            ${requestScope.successMsg}
            <c:out value="${requestScope.successMsg ? requestScope.successMsg : ''}" />
            ${requestScope.errMsg}
            <c:out value="${requestScope.errMsg ? requestScope.errMsg : ''}" />


            <p></p>
        </form>
                    <a src="/hms/edit"></a>
                    </div>

    </div>
</body>
</html>