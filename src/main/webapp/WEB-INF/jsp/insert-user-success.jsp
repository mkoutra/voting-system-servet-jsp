<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Voting</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container container-voting mt-4 bg-success text-light w-75">
        <div class="row mb-3 text-center mb-3 border-bottom">
            <h1><i class="bi bi-check-circle-fill"></i></h1>
            <h1>Successful Insertion</h1>
        </div>

        <div class="row mb-3 justify-content-center fs-3">
            <div class="col-6 text-end">
                <span>Username:</span>
            </div>
            <div class="col-6 justify-content-start">
                <span>${requestScope.userReadOnlyDTO.username}</span>
            </div>
        </div>

        <div class="row mb-3 justify-content-center fs-3">
            <div class="col-6 text-end">
                <span>First Name:</span>
            </div>
            <div class="col-6 justify-content-start">
                <span>${requestScope.userReadOnlyDTO.firstname}</span>
            </div>
        </div>

        <div class="row mb-3 pb-3 justify-content-center fs-3">
            <div class="col-6 text-end">
                <span>Last Name:</span>
            </div>
            <div class="col-6 justify-content-start">
                <span>${requestScope.userReadOnlyDTO.lastname}</span>
            </div>
        </div>
    </div>

    <div class="text-center">
        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Back to login page</a>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>