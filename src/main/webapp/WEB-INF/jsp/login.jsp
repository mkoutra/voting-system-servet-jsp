<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container container-login border border-2 border-dark-subtle rounded-4 mt-4">
        <div class="row text-center">
            <h1 class="display-3">Login</h1>
            <div class="text-center">
                <span class="fs-4 text-danger">${requestScope.loginErrorMessage}</span>
            </div>
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/login">
            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputUsername" class="col-form-label fs-5">Username</label>
                    <input type="text" class="form-control rounded-4" id="inputUsername" name="username" value="${requestScope.loginDTO.username}" required>
                </div>
            </div>

            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputPassword" class="col-form-label fs-5">Password</label>
                    <input type="password" class="form-control rounded-4" id="inputPassword" name="password" value="${requestScope.loginDTO.username}" required>
                </div>
            </div>

            <div class="row justify-content-center mb-3">
                <div class="col-sm-10 gy-3 text-center">
                    <button type="submit" class="btn btn-primary w-100 rounded-4">Sign in</button>
                </div>
            </div>
        </form>

        <div class="row text-center">
            <div class="col-sm gy-1 justify-content-center">
                <span class="fs-5">If you don't have an account:</span><br>
                <a href="${pageContext.request.contextPath}/users/register" type="button"
                   class="btn btn-secondary rounded-pill my-3 w-25">Sign up</a>
            </div>
        </div>
    </div>
</main>

<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>

</html>