$(function () {
    console.log("companies!");
    $("#companyNav").addClass("active");

    getCompany();
});

var getCompany = function() {
    $.get(url.company, function(data) {
        console.log(data);
    });
};