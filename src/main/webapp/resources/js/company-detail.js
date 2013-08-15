$(function () {
    console.log("companies!");
    $("#companyNav").addClass("active");

    getCompany();
});

var getCompany = function() {
    $.get(url.company, function(data) {
        console.log(data.company);
        $("#name").val(data.company.name);
        $("#tel").val(data.company.tel);
        $("#address").val(data.company.address);
    });
};