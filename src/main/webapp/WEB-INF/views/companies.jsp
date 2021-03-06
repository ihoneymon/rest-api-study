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
    <link href="<spring:url value="/resources/css/companies.css"/>" rel="stylesheet"/>

    <script src="<spring:url value="/resources/libs/jsrender/jsrender.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/companies.js" />"></script>
    <script id="companyTemplate" type="text/x-jsrender">
        <tr id="company-{{:id}}">
            <td><label>{{:id}}</label></td>
            <td><label><a href="<spring:url value="/view/companies/"/>{{:id}}">{{:name}}</a></label></td>
            <td><label>{{:tel}}</label></td>
            <td><label>{{:address}}</label></td>
            <td><a class="btn btn-primary" href="<spring:url value="/view/companies/"/>{{:id}}/departments"><spring:message code="view.department.label"/></a></td>
            <td><a class="btn btn-primary" href="<spring:url value="/view/companies/"/>{{:id}}/employees"><spring:message code="view.employee.label"/></a></td>
            <td>
                <button class="btn btn-modify-company" data-id="{{:id}}" data-name={{:name}} data-tel="{{:tel}}" data-address="{{:address}}"><i class="ui-icon-pencil"></i><spring:message code="view.btn.modify"/></button>
                <button class="btn btn-delete-company" data-id="{{:id}}"><i class="ui-icon-remove"></i><spring:message code="view.btn.delete"/></button>
            </td>
        </tr>
    </script>
    <script>
        var url = {
            companies: "<spring:url value="/api/companies"/>"
        };
        var labels = {
            add: "<spring:message code="view.common.label.add"/>",
            modify: "<spring:message code="view.common.label.modify"/>",
            delete: "<spring:message code="view.common.label.delete"/>"
        };
    </script>
</head>
<body>
<section>
    <article>
        <div class="col-12 mb10">
            <div class="col-12 mb10">
                <button class="btn btn-add-company pull-right"><i class="ui-icon-pencil"></i><spring:message code="view.btn.add"/></button>
            </div>
            <table id="companyTable" class="table table-bordered table-hover">
                <colgroup>
                    <col>
                    <col>
                    <col>
                    <col>
                    <co>
                    <col>
                </colgroup>
                <thead>
                    <tr>
                        <th><spring:message code="view.company.id"/></th>
                        <th><spring:message code="view.company.name"/></th>
                        <th><spring:message code="view.company.tel"/></th>
                        <th><spring:message code="view.company.address"/></th>
                        <th><spring:message code="view.company.departments"/></th>
                        <th><spring:message code="view.company.employees"/></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="7"><label><spring:message code="view.common.emptyData"/></label></td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </article>
</section>

    <section>
        <header><h3>Company REST API 설명</h3></header>
        <article>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>METHOD</th>
                    <th>URL</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>GET</td>
                    <td><code>/api/companies</code></td>
                    <td>기업목록 반환</td>
                </tr>
                <tr>
                    <td>GET</td>
                    <td><code>/api/companies/{companyId}</code></td>
                    <td>companyId는 고유식별 ID<br/>기업 상세정보</td>
                </tr>
                <tr>
                    <td>POST</td>
                    <td><code>/api/companies</code></td>
                    <td>
<pre><code>var form = {
    name: “”,
    tel: “”,
    address: “”
    }</code></pre>
                        결과확인 : data.code = 200(OK), 500(Server error)
<pre><code>var saveCompany = function() {
    getCompanies();

    var companyId = $("#companyModal").data("id");
    var form = {
        name: $("#name").val(),
        tel: $("#tel").val(),
        address: $("#address").val()
    };

    $.ajax({
        url: url.companies + "/" + companyId,
        method: "post",
        type: "json",
        contentType: "application/json",
        data: JSON.stringify(form),
        success: function() {
        $("#companyModal").modal("hide");
            getCompanies();
        }
    });
};
</code></pre>
                    </td>
                </tr>
                <tr>
                    <td>PUT</td>
                    <td><code>/api/companies/{companyId}</code></td>
                    <td>
<pre><code>var form = {
    name: “”,
    tel: “”,
    address: “”
}</code></pre>
                        결과확인 : data.code = 200(OK), 500(Server error)
                        data.code = 500인 경우, msg 확인
<pre><code>var updateCompany = function() {
    getCompanies();

    var companyId = $("#companyModal").data("id");
    var form = {
        name: $("#name").val(),
        tel: $("#tel").val(),
        address: $("#address").val()
    };

    $.ajax({
        url: url.companies + "/" + companyId,
        method: "put",
        type: "json",
        contentType: "application/json",
        data: JSON.stringify(form),
        success: function() {
        $("#companyModal").modal("hide");
            getCompanies();
        }
    });
};
</code></pre>
                    </td>
                </tr>
                <tr>
                    <td>DELETE</td>
                    <td><code>/api/companies/{companyId}</code></td>
                    <td>
<pre><code>var deleteCompany = function (companyId) {
    getCompanies();

    $.ajax({
        url: url.companies + "/" + companyId,
        method: "delete",
        type: "json",
        success: function () {
        $("#deleteCompanyModal").modal("hide");
            getCompanies();
        }
    });
};
</code></pre>
                    </td>
                </tr>
                </tbody>
            </table>
        </article>
    </section>

    <section>
        <!-- Add/Modify Confirm Modal -->
        <div class="modal fade" id="companyModal" tabindex="-1" role="dialog" aria-labelledby="companyModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title"><label><spring:message code="view.company.label"/></label><label id="modalTypeLabel"></label></h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="name" class="col-lg-3 control-label"><spring:message code="view.company.name"/></label>
                                <div class="col-lg-9">
                                    <input type="text" class="form-control" id="name" placeholder="<spring:message code="view.common.requiredOptionValue"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tel" class="col-lg-3 control-label"><spring:message code="view.company.tel"/></label>
                                <div class="col-lg-9">
                                    <input type="tel" class="form-control" id="tel" placeholder="<spring:message code="view.common.requiredOptionValue"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="col-lg-3 control-label"><spring:message code="view.company.address"/></label>
                                <div class="col-lg-9">
                                    <input type="text" class="form-control" id="address">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="view.btn.close"/></button>
                        <button type="button" class="btn btn-primary btn-confirm"><label id="modalButtonLabel"></label></button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <!-- Remove Company Modal -->
        <div class="modal fade" id="deleteCompanyModal" tabindex="-1" role="dialog" aria-labelledby="deleteCompanyModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title"><label><spring:message code="view.company.label.remove"/></label></h4>
                    </div>
                    <div class="modal-body">
                        <div>
                            <p><spring:message code="view.company.msg.remove"/></p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="view.btn.close"/></button>
                        <button type="button" class="btn btn-primary btn-delete-company-confirm"><spring:message code="view.btn.delete"/></button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </section>
</body>
</html>