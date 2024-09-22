<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Voting Results</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container mt-4">
        <div class="row justify-content-end mb-1">
            <a href="" class="col-sm-2 btn btn-outline-primary"><i class="bi bi-download"></i> Save</a>
        </div>

        <div class="row text-center mb-3">
            <h1>Results</h1>
            <span class="text-danger fs-4">${requestScope.errorMessage}</span>
            <span class="text-success fs-4">${requestScope.successMessage}</span>
        </div>

        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Number of Votes</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="candidateDTO" items="${requestScope.candidateWithVotesReadOnlyDTOs}">
                    <tr>
                        <th>${candidateDTO.cid}</th>
                        <td>${candidateDTO.firstname}</td>
                        <td>${candidateDTO.lastname}</td>
                        <td>${candidateDTO.totalVotes}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="text-center">
            <a href="${pageContext.request.contextPath}/voting" class="btn btn-outline-primary">Return</a>
        </div>
    </div>
</main>

<%@include file="/WEB-INF/jsp/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>