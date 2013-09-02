$(function() {
    console.log("employees!");
    $("#employeeNav").addClass("active");

    bindBtnAddEmployees();
    bindBtnAddEmployeeOfDepartment();
    bindBtnDeleteEmployeeOfDepartment();
    bindBtnDeleteEmployeeOfDepartmentConfirm();
    bindBtnConfirm();

    getEmployeesOfDepartment();
});

var getEmployeesOfDepartment = function() {
    $.get(url.employeesOfDepartment, function(data) {
        if(data.employees.content.length) {
            renderTemplate($("#employeeTable").find("tbody"), "#employeeTemplate", data.employees.content, true);
            $("#employeeTable").find("tfoot").hide();
        }
    });
};

var bindBtnAddEmployees = function() {
    $(document).on("click", ".btn-add-employee", function() {
        getEmployeesOfCompany();
        $("#employeeModal").modal("show");
    });
};

var bindBtnAddEmployeeOfDepartment = function() {
    $(document).on("click", ".btn-add-employee-to-department", function(event) {
        var employeeId = $(event.target).data("id");
        addEmployeeOfDepartment(employeeId);
    });
};

var addEmployeeOfDepartment = function(employeeId) {
    console.log(url.employeesOfDepartment);
    console.log(employeeId);
    var form = {
        employee: employeeId
    };

    $.ajax({
        url: url.employeesOfDepartment,
        method: "post",
        type: "json",
        data: form,
        success: function(data) {
            getEmployeesOfDepartment();
            $("#employeeModal").modal("hide");
        }
    });
}

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
    getEmployeesOfDepartment();

    $.ajax({
        url: url.employees + "/" + employeeId,
        method: "post",
        type: "json",
        contentType: "application/json",
        data: JSON.stringify(form),
        success: function(data) {
            $("#employeeModal").modal("hide");
            getEmployeesOfDepartment();
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
        getEmployeesOfDepartment();
        var employeeId = $("#deleteEmployeeModal").data("id");

        $.ajax({
            url: url.employeesOfDepartment + "/" + employeeId,
            method: "delete",
            type: "json",
            success: function() {
                getEmployeesOfDepartment();
                $("#deleteEmployeeModal").modal("hide");
            }
        });
    });
};

var getEmployeesOfCompany = function() {
    $.ajax({
        url: url.employeesOfCompany,
        method: "get",
        type: "json",
        success: function(data) {
            console.log(data);
            renderTemplate($("#employeesTable").find("tbody"), "#employeeOfDepartmentTemplate", data.employees.content, true);
        }
    });
}