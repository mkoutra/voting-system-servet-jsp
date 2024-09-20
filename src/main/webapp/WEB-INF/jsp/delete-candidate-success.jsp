<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Successful Deletion</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jsp/header.jsp"%>

<main>
    <div class="container mt-4 bg-success text-light w-75">
        <div class="row mb-3 text-center mb-3 border-bottom">
            <h1><i class="bi bi-check-circle-fill"></i></h1>
            <h1 class="mb-3">The candidate with ID ${requestScope.deletedCid} was deleted successfully.</h1>
        </div>
    </div>

    <div class="text-center">
        <a href="${pageContext.request.contextPath}/voting" class="btn btn-primary">Return</a>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>

</html>