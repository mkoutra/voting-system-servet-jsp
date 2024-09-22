<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/insert-candidate.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container container-insertion border border-2 border-dark-subtle rounded-4 mt-4">
        <div class="row text-center mb-3">
            <h1 class="display-5">Insert a Candidate</h1>
        </div>

        <form method="POST" action="">
            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputFirstname" class="col-form-label fs-5">First Name</label>
                    <input type="text" class="form-control rounded-4" id="inputFirstname" name="firstname" required>
                    <span class="text-danger">${requestScope.firstnameErrorMessage}</span>
                </div>
            </div>

            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputLastname" class="col-form-label fs-5">Last name</label>
                    <input type="text" class="form-control rounded-4" id="inputLastname" name="lastname" required>
                    <span class="text-danger">${requestScope.lastnameErrorMessage}</span>
                </div>
            </div>

            <div class="row justify-content-center mb-3">
                <div class="col-sm-10 gy-3 text-center">
                    <button type="submit" class="btn btn-primary w-100 rounded-4">Create Candidate</button>
                </div>
            </div>
        </form>
    </div>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/voting" class="btn btn-outline-primary">Return</a>
    </div>
</main>

<%@include file="/WEB-INF/jsp/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

<!-- Activate tooltips -->
<script>
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
</script>
</body>
</html>