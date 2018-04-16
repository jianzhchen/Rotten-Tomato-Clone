function addMovie() {
    if ($("#nameOfAddMovie").val() == "" || $("#dateOfAddMovie").val() == "" || $("#rateOfAddMovie").val() == "" || $("#boxOfficeOfAddMovie").val() == "") {
        //Movie is successfully added to repository in service by controller, display success message
        alert("Fail to add movie " + $("#nameOfAddMovie").val());
        return;
    }
    else {
        //Movie is not successfully added to repository in service by controller, display error message
        alert("Movie " + $("#nameOfAddMovie").val() + " has been added successfully!");
    }
    var error = true;
    $.post("/movie/addMovie",
        {
            "name": $("#nameOfAddMovie").val(),
            "date": $("#dateOfAddMovie").val(),
            "rate": $("#rateOfAddMovie").val(),
            "boxOffice": $("#boxOfficeOfAddMovie").val()
        },
        function (returnData) {
        });
}

function signupPost() {
    $.ajax({
        url: "/api0/auth/signup",
        type: "POST",
        data: JSON.stringify({
            "email": $("#signupInputE").val(),
            "username": $("#signupInputN").val(),
            "password": $("#signupInputP1").val(),
            "passwordConfirm": $("#signupInputP2").val(),
            "isCritic": $("#signupInputC").prop('checked')
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (returnData) {
            if (returnData.status === 'ok') {
                $("#signupOk").removeClass("d-none");
            }
            else {
                $("#signupError").removeClass("d-none").text(function (i, origText) {
                    return origText + "\n" + returnData.message;
                });
            }
            console.log(returnData);
        }
    });
}
function init() {

    var textBeforeRate = "<a class=\"list-group-item py-0 pt-1 border-0\">" +
        "    <div class=\"container row px-0 mx-0\">" +
        "    <div class=\"'col-1 mx-0\">" +
        "    <i class=\"ico-rank1\">";
    var textBtwRateAndName = "%</i>" +
        "    </div>\n" +
        "    <div class='col-md-7 mx-0'>" +
        "    <p>";
    var textBtwNameAndBoxOffice = "</p>" +
        "</div>\n" +
        "<div class=\"'col-md-1 mx-0\">" +
        "    <p>$";
    var last = "</p>" +
        "</div>" +
        "</div>" +
        "</a>";

    var list = document.getElementById("topBoxOffice");
    var context="";

    $.get("/movie/getTopBoxOffice", function (returndata) {
        console.log(returndata)
        for(var i=0;i<10;i++){
            context=context+textBeforeRate;
            context=context+returndata[i].rate;
            context=context+textBtwRateAndName;
            context=context+returndata[i].movieName;
            context=context+textBtwNameAndBoxOffice;
            context=context+returndata[i].boxOffice;
            context=context+last;
        }
        list.innerHTML=context;

    });
}




