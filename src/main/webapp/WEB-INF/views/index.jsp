<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
    <title>RestAPI Test page</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- stylesheet -->
    <link href="<spring:url value="/resources/libs/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

	<!-- javascript -->    
    <script src="<spring:url value="/resources/libs/jquery/jquery-1.9.1.min.js" />"></script>
    <script src="<spring:url value="/resources/libs/bootstrap/js/bootstrap.min.js" />"></script>
</head>
<body>
<div>
	<h3>Hello!</h3>
</div>
    <script type="text/javascript">
	$(function() {
		console.log("234");
	});
    </script>
</body>
</html>