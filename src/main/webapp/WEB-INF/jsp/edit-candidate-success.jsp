<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidate Update Success</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container mt-4 bg-success text-light w-75">
        <div class="row mb-3 text-center mb-3 border-bottom">
            <h1><i class="bi bi-check-circle-fill"></i></h1>
            <h1>Successful Candidate Update</h1>
        </div>

        <div class="row mb-3 justify-content-center fs-3">
            <div class="col-6 text-end">
                <span>ID:</span>
            </div>
            <div class="col-6 justify-content-start">
                <span>${requestScope.candidateReadOnlyDTO.cid}</span>
            </div>
        </div>

        <div class="row mb-3 justify-content-center fs-3">
            <div class="col-6 text-end">
                <span>First Name:</span>
            </div>
            <div class="col-6 justify-content-start">
                <span>${requestScope.candidateReadOnlyDTO.firstname}</span>
            </div>
        </div>

        <div class="row mb-3 pb-3 justify-content-center fs-3">
            <div class="col-6 text-end">
                <span>Last Name:</span>
            </div>
            <div class="col-6 justify-content-start">
                <span>${requestScope.candidateReadOnlyDTO.lastname}</span>
            </div>
        </div>
    </div>

    <div class="text-center">
        <a href="${pageContext.request.contextPath}/voting" class="btn btn-primary">Return</a>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>

</html>