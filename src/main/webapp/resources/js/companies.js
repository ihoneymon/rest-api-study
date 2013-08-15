$(function () {
    console.log("companies!");
    $("#companyNav").addClass("active");
    bindBtnAddCompany();
    bindBtnModifyCompany();
    bindBtnRemoveCompany();

    getCompanies();
});

var bindBtnAddCompany = function () {
    $(document).on("click", ".btn-add-company", function () {
        modalCompany("post");
    });
};

var bindBtnModifyCompany = function () {
    $(document).on("click", ".btn-modify-company", function (event) {
        modalCompany("put", $(event.target).data("id"));
    });
};

var bindBtnRemoveCompany = function () {
    $(document).on("click", ".btn-remove-company", function (event) {
        modalCompany("delete", $(event.target).data("id"));
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