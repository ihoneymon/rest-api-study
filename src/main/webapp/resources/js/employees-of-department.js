$(function() {
    console.log("employees!");
    $("#employeeNav").addClass("active");

    bindBtnAddEmployeeOfDepartment();
    bindBtnDeleteEmployeeOfDepartment();
    bindBtnDeleteEmployeeOfDepartmentConfirm();
    bindBtnConfirm();

    getEmployees();
});

var getEmployees = function() {
    $.get(url.employees, function(data) {
        if(data.employees.content.length) {
            renderTemplate($("#employeeTable").find("tbody"), "#employeeTemplate", data.employees.content, true);
            $("#employeeTable").find("tfoot").hide();
        }
    });
};

var bindBtnAddEmployeeOfDepartment = function() {
    $(document).on("click", ".btn-add-employee", function() {
        employeeModal("post");
        $("#employeeModal").modal("show");
    });
};

var employeeModal = function (method, employeeId) {
    var methodLabel = "";
    if (method === "post") {
        methodLabel = labels.add;
    } else if (method === "put") {
        methodLabel = labels.modify;
    }
    $("#modalTypeLabel").text(methodLabel);
    $("#modalButtonLabel").text(methodLabel);

    $("#employeeModal").data("method", method);
    $("#employeeModal").data("id", employeeId);
    $("input.form-control").val("");
    $("#employeeModal").modal("show");
};

var bindBtnConfirm = function() {
    $(document).on("click", ".btn-confirm", function() {
        var method = $("#employeeModal").data("method");
        if(method === "post") {
            saveEmployee();
        } else if(method === "put") {
            updateEmployee();
        }
    });
};

var saveEmployee = function(employeeId) {
    getEmployees();

    $.ajax({
        url: url.employees + "/" + employeeId,
        method: "post",
        type: "json",
        contentType: "application/json",
        data: JSON.stringify(form),
        success: function(data) {
            $("#employeeModal").modal("hide");
            getEmployees();
        }
    });
};

var bindBtnDeleteEmployeeOfDepartment = function() {
    $(document).on("click", ".btn-delete-employee-from-department", function(event) {
        $("#deleteEmployeeModal").data("id", $(event.target).data("id"));
        $("#deleteEmployeeModal").modal("show");
    });
};

var bindBtnDeleteEmployeeOfDepartmentConfirm = function() {
    $(document).on("click", ".btn-delete-employee-of-department-confirm", function() {
        getEmployees();
        var employeeId = $("#deleteEmployeeModal").data("id");

        $.ajax({
            url: url.employees + "/" + employeeId,
            method: "delete",
            type: "json",
            success: function(data) {
                $("#deleteEmployeeModal").modal("hide");
                getEmployees();
            }
        });
    });
};