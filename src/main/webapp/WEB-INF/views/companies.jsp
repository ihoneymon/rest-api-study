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
    <script id="companyTemplate" type="text/x-jsrender">
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
    <script>
        var url = {
            companies: "<spring:url value="/api/companies"/>"
        }
    </script>
</head>
<body>
    <div class="col-12">
        <div class="col-12 mb10">
            <button class="btn btn-add-company pull-right"><i class="ui-icon-pencil"></i><spring:message code="view.btn.add"/></button>
        </div>
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
                    <th><spring:message code="view.company.id"/></th>
                    <th><spring:message code="view.company.name"/></th>
                    <th><spring:message code="view.company.tel"/></th>
                    <th><spring:message code="view.company.address"/></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>

            </tbody>
            <tfoot>
                <tr>
                    <td colspan="5"><label><spring:message code="view.company.emptyData"/></label></td>
                </tr>
            </tfoot>
        </table>
    </div>

    <section>
        <header><label>Company REST API 설명</label></header>
        <article></article>
    </section>

    <section>
        <!-- Button trigger modal -->
        <a data-toggle="modal" href="#modalCompany" class="btn btn-primary btn-lg">Launch demo modal</a>

        <!-- Modal -->
        <div class="modal fade" id="modalCompany" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </section>
</body>
</html>