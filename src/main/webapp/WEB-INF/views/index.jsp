<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
    <title>RestAPI Test page</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- stylesheet -->
    <link href="<spring:url value="/resources/libs/jquery-ui/css/redmond/jquery-ui-1.10.3.custom.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/libs/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- javascript -->
    <script src="<spring:url value="/resources/libs/jquery/jquery-1.10.2.min.js" />"></script>
    <script src="<spring:url value="/resources/libs/jquery-ui/js/jquery-ui-1.10.3.custom.min.js" />"></script>
    <script src="<spring:url value="/resources/libs/bootstrap/js/bootstrap.min.js" />"></script>
</head>
<body>
<header>
    <h3>REST API example</h3>
</header>

<section>
    <nav>
        <div class="col-2">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="<spring:url value="/companies"/>">Company 관련 API</a></li>
                <li><a href="<spring:url value="/departments" />">Department 관련 API</a></li>
                <li><a href="<spring:url value="/employees" />">Employee 관련 API</a></li>
            </ul>
        </div>
    </nav>
    <section>

    </section>
</section>
</body>
</html>