<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

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

<%@include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container container-voting mt-4">
        <c:if test="${sessionScope.username eq 'admin'}">
            <div class="row justify-content-end mb-1">
                <a href="${pageContext.request.contextPath}/voting/candidate/insert" class="col-sm-2 btn btn-outline-primary">Insert Candidate</a>
            </div>
        </c:if>

        <div class="row justify-content-end mb-4">
            <a href="${pageContext.request.contextPath}/voting/results" class="col-sm-2 btn btn-outline-primary">Results</a>
        </div>

        <div class="row text-center mb-3">
            <h1>Candidates</h1>
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
                    <c:if test="${sessionScope.username eq 'admin'}">
                        <th scope="col">Votes</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </c:if>
                    <th scope="col">Place Vote</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="candidateDTO" items="${requestScope.candidateWithVotesReadOnlyDTOs}">
                    <tr>
                        <th>${candidateDTO.cid}</th>
                        <td>${candidateDTO.firstname}</td>
                        <td>${candidateDTO.lastname}</td>
                        <c:if test="${sessionScope.username eq 'admin'}">
                            <td>${candidateDTO.totalVotes}</td>
                            <td><a href="${pageContext.request.contextPath}/voting/candidate/edit?cid=${candidateDTO.cid}&firstname=${candidateDTO.firstname}&lastname=${candidateDTO.lastname}"
                                   class="btn btn-success"><i class="bi bi-pencil-square"></i></a></td>
                            <td><a href="${pageContext.request.contextPath}/voting/candidate/delete?cid=${candidateDTO.cid}"
                                   class="btn btn-danger"><i class="bi bi-x"></i></a></td>
                        </c:if>
                        <%-- Check if the user has voted or not to activate voting button --%>
                        <c:if test="${sessionScope.hasVoted == false}">
                            <td><a href="${pageContext.request.contextPath}/voting/candidate/vote?cid=${candidateDTO.cid}" class="btn btn-primary">Vote</a></td>
                        </c:if>
                        <c:if test="${sessionScope.hasVoted == true}">
                            <td><a class="btn btn-primary disabled" aria-disabled="true">Vote</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>

<%@include file="/WEB-INF/jsp/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>