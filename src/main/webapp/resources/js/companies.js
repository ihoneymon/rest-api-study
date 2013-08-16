$(function () {
    console.log("companies!");
    $("#companyNav").addClass("active");
    $("#departmentNav").hide();
    $("#employeeNav").hide();

    bindBtnAddCompany();
    bindBtnUpdateCompany();
    bindBtnDeleteCompany();
    bindBtnConfirm();
    bindBtnDeleteCompanyConfirm();

    getCompanies();
});

var bindBtnAddCompany = function () {
    $(document).on("click", ".btn-add-company", function () {
        $("input.form-control").val("");
        modalCompany("post");
    });
};

var bindBtnUpdateCompany = function () {
    $(document).on("click", ".btn-modify-company", function (event) {
        $("#name").val($(event.target).data("name"));
        $("#tel").val($(event.target).data("tel"));
        $("#address").val($(event.target).data("address"));
        modalCompany("put", $(event.target).data("id"));
    });
};

var bindBtnDeleteCompany = function () {
    $(document).on("click", ".btn-delete-company", function (event) {
        $("#deleteCompanyModal").data("id", $(event.target).data("id"));
        $("#deleteCompanyModal").modal("show");
    });
};

var modalCompany = function (method, companyId) {
    var methodLabel = "";
    if (method === "post") {
        methodLabel = labels.add;
    } else if (method === "put") {
        methodLabel = labels.modify;
    }
    $("#modalTypeLabel").text(methodLabel);
    $("#modalButtonLabel").text(methodLabel);

    $("#companyModal").data("method", method);
    $("#companyModal").data("id", companyId);
    $("#companyModal").modal("show");
};

/**
 * 기업정보 가져오기
 */
var getCompanies = function () {
    $.get(url.companies, function (data) {
        if (data.companies.content.length) {
            renderTemplate($("#companyTable").find("tbody"), "#companyTemplate", data.companies.content, true);
            $("#companyTable").find("tfoot").hide();
        }
    });
};

var bindBtnConfirm = function () {
    $(document).on("click", ".btn-confirm", function () {
        var method = $("#companyModal").data("method");
        if (method === "post") {
            saveCompany();
        } else if (method === "put") {
            updateCompany();
        }
    });
};

var saveCompany = function() {
    console.log("saveCompany");
    var form = {
        name: $("#name").val(),
        tel: $("#tel").val(),
        address: $("#address").val()
    }

    console.log(form);
    getCompanies();

    $.ajax({
        url: url.companies,
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

var updateCompany = function() {
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

var bindBtnDeleteCompanyConfirm = function () {
    $(document).on("click", ".btn-delete-company-confirm", function () {
        var companyId = $("#deleteCompanyModal").data("id");
        deleteCompany(companyId);
    });
};

var deleteCompany = function (companyId) {
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