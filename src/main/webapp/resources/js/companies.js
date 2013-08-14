$(function() {
    console.log("companies!");
    $("#companyNav").addClass("active");
    bindBtnAddCompany();
    bindBtnModifyCompany();
    bindBtnRemoveCompany();

    getCompanies();
});

var bindBtnAddCompany = function() {
    $(".btn-add-company").on("click", function() {
        modalCompany("post");
    });
};

var bindBtnModifyCompany = function() {
    $(document).on("click", ".btn-modify-company", function(event) {
        modalCompany("put", $(event.target));
    });
};

var bindBtnRemoveCompany = function() {
    $(document).on("click", ".btn-remove-company", function(event) {
        modalCompany("delete", $(event.target));
    });
};

var modalCompany = function(method, $target) {
    console.log("method : " + method);
    if($target.data("id")) {
        console.log($target.data("id"));
    }


};

/**
 * 기업정보 가져오기
 */
var getCompanies = function() {
    $.get(url.companies, function(data) {
        console.log(data.companies);
        renderTemplate($("#companyTable").find("tbody"), "#companyTemplate", data.companies.content, true);
    });
};