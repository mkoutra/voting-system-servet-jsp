<%--
    @author: Michail E. Koutrakis
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Voting System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jsp/header.jsp" %>

<main>
    <div class="main d-flex flex-column justify-content-center align-items-center">
        <h1 class="text-center">Welcome to voting system application.</h1>

        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/login" type="button" class="btn btn-primary btn-lg">Enter</a>
        </div>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>

</body>
</html>