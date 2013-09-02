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
    <title>Departments REST API Page</title>
    <meta name="decorator" content="common">
    <link href="<spring:url value="/resources/css/departments.css"/>" rel="stylesheet"/>

    <script src="<spring:url value="/resources/libs/jsrender/jsrender.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/departments.js" />"></script>
    <script id="departmentTemplate" type="text/x-jsrender">
        <tr id="department-{{:id}}">
            <td><label>{{:id}}</label></td>
            <td><label>{{:name}}</label></td>
            <td><label>{{:description}}</label></td>
            <td><a class="btn" href="<spring:url value="/view/companies/${companyId}/departments/"/>{{:id}}/employees"><spring:message code="view.employee.label"/></a></td>
            <td>
                <button class="btn btn-update-department" data-id="{{:id}}" data-name={{:name}} data-description="{{:description}}"><i class="ui-icon-pencil"></i><spring:message code="view.btn.modify"/></button>
                <button class="btn btn-delete-department" data-id="{{:id}}"><i class="ui-icon-remove"></i><spring:message code="view.btn.delete"/></button>
            </td>
        </tr>
    </script>
    <script>
        var url = {
            companies: "<spring:url value="/api/companies"/>",
            departments: "<spring:url value="/api/companies/${companyId}/departments"/>"
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
                <button class="btn btn-add-department pull-right"><i class="ui-icon-pencil"></i><spring:message code="view.btn.add"/></button>
            </div>
            <table id="departmentTable" class="table table-bordered table-hover">
                <colgroup>
                    <col>
                    <col>
                    <col>
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th><spring:message code="view.department.id"/></th>
                    <th><spring:message code="view.department.name"/></th>
                    <th><spring:message code="view.department.description"/></th>
                    <th><spring:message code="view.department.employees"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                </tbody>
                <tfoot>
                <tr>
                    <td colspan="5"><spring:message code="view.common.emptyData"/></td>
                </tr>
                </tfoot>
            </table>
        </div>
    </article>
</section>

<section>
    <header><h3>Department REST API 설명</h3></header>
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
                <td><code>/api/companies/{companyId}/departments</code></td>
                <td>기업의 부서목록 반환</td>
            </tr>
            <tr>
                <td>GET</td>
                <td><code>/api/companies/{companyId}/departments/{departmentId}</code></td>
                <td>부서의 상세정보</td>
            <tr>
            </tr>
            <td>POST</td>
            <td><code>/api/companies/{companyId}/departments</code></td>
            <td>
<pre><code>
var form = {
    name: “”,
    description: “”
}
</code></pre>
                결과확인 : data.code = 200(OK), 500(Server error)
<pre><code>
var saveDepartment = function() {
    getDepartments();

    var form = {
        name: $(“#name”).val(),
        description: $(“#description”).val()
    };

    $.ajax({
        url: url.departments,
        method: “post”,
        type: “json”,
        contentType: “application/json”,
        data: JSON.stringify(form),
        success: function(data) {
        $(“#departmentModal”).modal(“hide”);
            getDepartments();
        }
    });
};
</code></pre>
            </td>
            </tr>
            <tr>
                <td>PUT</td>
                <td><code>/api/companies/{companyId}/departments/{departmentId}</code></td>
                <td>
<pre><code>
var form = {
    name: “”,
    description: “”
}
</code></pre>
                    결과확인 : data.code = 200(OK), 500(Server error)
<pre><code>
var updateDepartment = function() {
    getDepartments();

    var form = {
        name: $(“#name”).val(),
        description: $(“#description”).val()
    };

    $.ajax({
        url: url.departments,
        method: “update”,
        type: “json”,
        contentType: “application/json”,
        data: JSON.stringify(form),
        success: function(data) {
            $(“#departmentModal”).modal(“hide”);
            getDepartments();
        }
    });
};
</code></pre>
                </td>
            </tr>
            <tr>
                <td>DELETE</td>
                <td><code>/api/companies/{companyId}/departments/{departmentId}</code></td>
                <td>
<pre><code>
var deleteDepartment = function() {
    getDepartments();
    var departmentId = $(“#deleteDepartmentModal”).data(“id”);

    $.ajax({
        url: url.departments + “/” + departmentId,
        method: “delete”,
        type: “json”,
        success: function(data) {
        $(“#deleteDepartmentModal”).modal(“hide”);
            getDepartments();
        }
    });
}
</code></pre>
                </td>
            <tr>
            </tbody>
        </table>
    </article>
</section>

<section>
    <!-- Add/Modify Confirm Modal -->
    <div class="modal fade" id="departmentModal" tabindex="-1" role="dialog" aria-labelledby="departmentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><label><spring:message code="view.department.label"/></label><label id="modalTypeLabel"></label></h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="name" class="col-lg-3 control-label"><spring:message code="view.department.name"/></label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control" id="name" placeholder="<spring:message code="view.common.requiredOptionValue"/>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-lg-3 control-label"><spring:message code="view.department.description"/></label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control" id="description" placeholder="<spring:message code="view.common.requiredOptionValue"/>">
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
    <div class="modal fade" id="deleteDepartmentModal" tabindex="-1" role="dialog" aria-labelledby="deletedepartmentModalLabel" aria-hidden="true">
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
                    <button type="button" class="btn btn-primary btn-delete-department-confirm"><spring:message code="view.btn.delete"/></button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</section>
</body>
</html>