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
    <title>Employees REST API Page</title>
    <meta name="decorator" content="common">
    <link href="<spring:url value="/resources/css/employees.css"/>" rel="stylesheet"/>

    <script src="<spring:url value="/resources/libs/jsrender/jsrender.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/employees-of-department.js" />"></script>

    <script id="employeeOfDepartmentTemplate" type="text/x-jsrender">
        <tr id="employee-of-department-{{:id}}">
            <td><label>{{:id}}</label></td>
            <td><label>{{:name}}</label></td>
            <td>
                <button class="btn btn-add-employee-to-department" data-id="{{:id}}"><i class="ui-icon-plus"></i><spring:message code="view.btn.add"/></button>
            </td>
        </tr>
    </script>

    <script id="employeeTemplate" type="text/x-jsrender">
        <tr id="employee-{{:id}}">
            <td><label>{{:id}}</label></td>
            <td><label>{{:name}}</label></td>
            <td><label>{{:nickName}}</label></td>
            <td><label>{{:email}}</label></td>
            <td>
                <button class="btn btn-delete-employee-from-department" data-id="{{:id}}"><i class="ui-icon-remove"></i><spring:message code="view.btn.delete"/></button>
            </td>
        </tr>
    </script>

    <script>
        var url = {
            companies: "<spring:url value="/api/companies"/>",
            employeesOfCompany: "<spring:url value="/api/companies/${companyId}/employees"/>",
            departments: "<spring:url value="/api/companies/${companyId}/departments"/>",
            employeesOfDepartment: "<spring:url value="/api/companies/${companyId}/departments/${departmentId}/employees"/>"
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
                <button class="btn btn-add-employee pull-right"><i class="ui-icon-pencil"></i><spring:message code="view.btn.add"/></button>
            </div>
            <table id="employeeTable" class="table table-bordered table-hover">
                <colgroup>
                    <col>
                    <col>
                    <col>
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th><spring:message code="view.employee.id"/></th>
                    <th><spring:message code="view.employee.name"/></th>
                    <th><spring:message code="view.employee.nickName"/></th>
                    <th><spring:message code="view.employee.email"/></th>
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
    <header><h3>employee REST API 설명</h3></header>
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
                <td><code>/api/companies/{companyId}/departments/{departmentId}/employees</code></td>
                <td>부서에 소속된 직원정보를 열람</td>
            </tr>
            <tr>
                <td>POST</td>
                <td><code>/api/companies/{companyId}/departments/{departmentId}/employees/{employeeId}</code></td>
                <td>
<pre><code>
var saveEmployee = function(employeeId) {
    getEmployees();

    $.ajax({
        url: url.employees + “/” + employeeId,
        method: “post”,
        type: “json”,
        contentType: “application/json”,
        data: JSON.stringify(form),
        success: function(data) {
        $(“#employeeModal”).modal(“hide”);
            getEmployees();
        }
    });
};
</code></pre>
                </td>
            </tr>
            <tr>
                <td>DELETE</td>
                <td><code>/api/companies/{companyId}/departments/{departmentId}/employees/{employeeId}</code></td>
                <td>
<pre><code>
var removeDepartmentFromEmployee = function(employeeId) {
    getEmployees();

    $.ajax({
        url: url.employees + “/” + employeeId,
        method: “delete”,
        type: “json”,
        success: function(data) {
            getEmployees();
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
    <div class="modal fade" id="employeeModal" tabindex="-1" role="dialog" aria-labelledby="employeeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><label><spring:message code="view.employee.label"/></label><label id="modalTypeLabel"></label></h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="employeesTable">
                        <thead>
                        <tr>
                            <th><spring:message code="view.employee.id"/></th>
                            <th><spring:message code="view.employee.name"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="view.btn.close"/></button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- Remove Company Modal -->
    <div class="modal fade" id="deleteEmployeeModal" tabindex="-1" role="dialog" aria-labelledby="deleteEmployeeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><label><spring:message code="view.company.label.remove"/></label></h4>
                </div>
                <div class="modal-body">
                    <div>
                        <p><spring:message code="view.employee.msg.removeFromDepartment"/></p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="view.btn.close"/></button>
                    <button type="button" class="btn btn-primary btn-delete-employee-of-department-confirm"><spring:message code="view.btn.delete"/></button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</section>
</body>
</html>