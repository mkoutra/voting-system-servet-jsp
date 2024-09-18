<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit-candidate.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container container-insertion border border-2 border-dark-subtle rounded-4 mt-4">
        <div class="row text-center mb-3">
            <h1 class="display-5">Edit Candidate</h1>
            <span class="text-danger fs-4">${requestScope.errorMessage}</span>
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/candidate/edit">
            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label class="col-form-label fs-5">ID</label>
                    <input type="text" class="form-control rounded-4" name="cid" value="${requestScope.candidateUpdateDTO.cid}" readonly>
                </div>
            </div>

            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputFirstname" class="col-form-label fs-5">First Name</label>
                    <input type="text" class="form-control rounded-4" id="inputFirstname" name="firstname" value="${requestScope.candidateUpdateDTO.firstname}" required>
                    <span class="text-danger">${requestScope.firstnameErrorMessage}</span>
                </div>
            </div>

            <div class="row mb-3 justify-content-center">
                <div class="col-sm-10">
                    <label for="inputLastname" class="col-form-label fs-5">Last name</label>
                    <input type="text" class="form-control rounded-4" id="inputLastname" name="lastname" value="${requestScope.candidateUpdateDTO.lastname}" required>
                    <span class="text-danger">${requestScope.lastnameErrorMessage}</span>
                </div>
            </div>

            <div class="row justify-content-center mb-3">
                <div class="col-sm-10 gy-3 text-center">
                    <button type="submit" class="btn btn-primary w-100 rounded-4">Update Candidate</button>
                </div>
            </div>
        </form>
    </div>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/voting" class="btn btn-outline-primary">Return</a>
    </div>
</main>

<%@include file="/WEB-INF/jsp/footer.jsp"%>

</body>
</html>