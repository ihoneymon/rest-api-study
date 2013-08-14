<%--
  Created by IntelliJ IDEA.
  User: ihoneymon
  Date: 13. 8. 14
  Time: 오전 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Companies REST API Page</title>
    <meta name="decorator" content="common">

    <script src="<spring:url value="/resources/libs/jsrender/jsrender.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/companies.js" />"></script>
    <script id="companyTmpl" type="text/x-jsrender">
        <tr id="{{:id}}">
            <td>{{:id}}</td>
            <td>{{:name}}</td>
            <td>{{:tel}}</td>
            <td>{{:address}}</td>
            <td>
                <button class="btn btn-modify-company" data-id="{{:id}}"><i class="ui-icon-pencil"></i><spring:message code="view.btn.modify"/></button>
                <button class="btn btn-remove-company" data-id="{{:id}}"><i class="ui-icon-remove"></i><spring:message code="view.btn.remove"/></button>
            </td>
        </tr>
    </script>
</head>
<body>
    <div class="col-12">
        <table id="companyTable" class="table table-bordered table-hover">
            <colgroup>
                <col>
                <col>
                <col>
                <col>
                <col>
            </colgroup>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Tel</th>
                    <th>Address</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>

    <section>
        <header><label>Company REST API 설명</label></header>
        <article></article>
    </section>
</body>
</html>