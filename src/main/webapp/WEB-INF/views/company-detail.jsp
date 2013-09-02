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
    <title>Companies Detail Page</title>
    <meta name="decorator" content="common">
    <link href="<spring:url value="/resources/css/company-detail.css"/>" rel="stylesheet"/>

    <script src="<spring:url value="/resources/js/company-detail.js" />"></script>
    <script>
        var variables = {
            companyId: ${companyId}
        };
        var url = {
            company: "<spring:url value="/api/companies/"/>" + variables.companyId
        };
    </script>
</head>
<body>
    <section class="company-detail">
        <header><label><spring:message code="view.company.label.detail"/></label></header>
        <article>
            <div class="col-12">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-2 control-label"><spring:message code="view.company.name"/></label>
                        <div class="col-10">
                            <p id="name" class="form-control-static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tel" class="col-2 control-label"><spring:message code="view.company.tel"/></label>
                        <div class="col-10">
                            <input type="text" class="form-control" id="tel" placeholder="<spring:message code="view.common.requiredOptionValue"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-2 control-label"><spring:message code="view.company.address"/></label>
                        <div class="col-10">
                            <input type="text" class="form-control" id="address">
                        </div>
                    </div>
                </form>
            </div>
        </article>
    </section>
</body>
</html>