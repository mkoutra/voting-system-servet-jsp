<%--
    @author: Michail E. Koutrakis
--%>
<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-sm">
            <div class="container-sm">
                <a class="navbar-brand" href=""><span class="display-4">Voting System</span></a>
                <c:if test="${sessionScope.username != null}">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                        <ul class="navbar-nav mb-2 mb-lg-0">
                            <li class="nav-item dropdown fs-4">
                                <a class="nav-link dropdown-toggle" href="" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    <img class="" src="${pageContext.request.contextPath}/img/userIcon_x_small.png" alt="Username logo">
                                    <span>${sessionScope.username}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item fs-5" href="${pageContext.request.contextPath}/user/view-vote">View vote</a></li>
                                    <li><a class="dropdown-item fs-5" href="${pageContext.request.contextPath}/user/change-password">Change password</a></li>
                                    <li><a class="dropdown-item fs-5" href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:if>
            </div>
        </nav>
    </header>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</body>
</html>
