<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ page session="false" %>

<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- stylesheet -->
    <link href="<spring:url value="/resources/libs/jquery-ui/css/redmond/jquery-ui-1.10.3.custom.min.css" />" rel="stylesheet"/>
    <link href="<spring:url value="/resources/libs/bootstrap/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<spring:url value="/resources/css/common.css"/>" rel="stylesheet"/>

    <!-- javascript -->
    <script src="<spring:url value="/resources/libs/jquery/jquery-1.10.2.min.js" />"></script>
    <script src="<spring:url value="/resources/libs/jquery-ui/js/jquery-ui-1.10.3.custom.min.js" />"></script>
    <script src="<spring:url value="/resources/libs/bootstrap/js/bootstrap.min.js" />"></script>

    <decorator:head/>
</head>

<body>
<header>
    <h3><a href="<spring:url value="/view/"/>"><spring:message code="view.common.restAPIStudy"/></a></h3>
</header>

<nav>
    <div class="col-2">
        <ul class="nav nav-pills nav-stacked">
            <li id="companyNav"><a href="<spring:url value="/view/companies"/>"><spring:message code="view.common.companyAPI"/></a></li>
            <li id="departmentNav"><a href="<spring:url value="/view/departments" />"><spring:message code="view.common.departmentAPI"/></a></li>
            <li id="employeeNav"><a href="<spring:url value="/view/employees" />" ><spring:message code="view.common.employeeAPI"/></a></li>
        </ul>
    </div>
</nav>

<section>
    <div class="col-10">
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="view.common.aboutRestAPI"/></h3>
            </div>
            <decorator:body/>
        </div>
    </div>
</section>
</body>
</html>