$(function () {
    console.log("companies!");
    $("#companyNav").addClass("active");
    bindBtnAddCompany();
    bindBtnUpdateCompany();
    bindBtnDeleteCompany();
    bindBtnConfirm();
    bindBtnDeleteCompanyConfirm();

    getCompanies();
});

var bindBtnAddCompany = function () {
    $(document).on("click", ".btn-add-company", function () {
        modalCompany("post");
    });
};

var bindBtnUpdateCompany = function () {
    $(document).on("click", ".btn-modify-company", function (event) {
        modalCompany("put", $(event.target).data("id"));
    });
};

var bindBtnDeleteCompany = function () {
    $(document).on("click", ".btn-delete-company", function (event) {
        console.log($(event.target).data("id"));
        $("#deleteCompanyModal").data("id", $(event.target).data("id"));
        $("#deleteCompanyModal").modal("show");
    });
};

var modalCompany = function (method, targetId) {
    console.log("method : " + method);
    console.log(targetId);

    var methodLabel = "";
    if (method === "post") {
        methodLabel = labels.add;
    } else if (method === "put") {
        methodLabel = labels.modify;
    } else if (method === "delete") {
        methodLabel = labels.delete;
    }
    $("#modalTypeLabel").text(methodLabel);
    $("#modalButtonLabel").text(methodLabel);
    $("#companyModal").data("method", method);
    $("#companyModal").modal("show");
};

/**
 * 기업정보 가져오기
 */
var getCompanies = function () {
    $.get(url.companies, function (data) {
        if (data.companies.content) {
            renderTemplate($("#companyTable").find("tbody"), "#companyTemplate", data.companies.content, true);
            $("#companyTable").find("tfoot").hide();
        }
    });
};

var bindBtnConfirm = function() {
    $(document).on("click", ".btn-confirm", function() {
        var method = $("#companyModal").data("method");
        if(method === "post") {

        }
    });
};

var bindBtnDeleteCompanyConfirm = function() {
    $(document).on("click", ".btn-delete-company-confirm", function() {
        var companyId = $("#deleteCompanyModal").data("id");
        deleteCompany(companyId);
    });
};

var deleteCompany = function(companyId) {
    $.ajax({
        url: url.companies + "/" + companyId,
        method: "delete",
        type: "json",
        success: function() {
            $("#deleteCompanyModal").modal("hide");
        }
    });
};