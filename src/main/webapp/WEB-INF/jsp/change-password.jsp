<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="../css/insert-candidate.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container container-insertion border border-2 border-dark-subtle rounded-4 mt-4">
        <div class="row text-center mb-3">
            <h1 class="display-5">Change Password</h1>
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/user/change-password">
            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputCurrentPassword" class="col-form-label fs-5">Current Password</label>
                    <input type="password" class="form-control rounded-4" id="inputCurrentPassword"
                           name="currentPassword" value="${requestScope.changePasswordDTO.currentPassword}" required>
                    <span class="text-danger fs-5">${requestScope.errorsDTO.currentPasswordError}</span>
                </div>
            </div>

            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputNewPassword" class="col-form-label fs-5">New Password</label>
                    <input type="password" class="form-control rounded-4" id="inputNewPassword"
                           name="newPassword" value="${requestScope.changePasswordDTO.newPassword}" required>
                    <span class="text-danger fs-5">${requestScope.errorsDTO.newPasswordError}</span>
                </div>
            </div>

            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputNewReEnteredPassword" class="col-form-label fs-5">Confirm Password</label>
                    <input type="password" class="form-control rounded-4" id="inputNewReEnteredPassword"
                           name="reEnteredPassword" value="${requestScope.changePasswordDTO.reEnteredPassword}" required>
                    <span class="text-danger fs-5">${requestScope.errorsDTO.reEnteredPasswordError}</span>
                </div>
            </div>

            <div class="row justify-content-center mb-3">
                <div class="col-sm-10 gy-3 text-center">
                    <button type="submit" class="btn btn-primary w-100 rounded-4">Change Password</button>
                </div>
            </div>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/jsp/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>