function addMovie() {
    $.post("/movie/addMovie",
        {
            "name": $("#nameOfAddMovie").val(),
            "date": $("#dateOfAddMovie").val(),
            "rate": $("#rateOfAddMovie").val(),
            "boxOffice": $("#boxOfficeOfAddMovie").val()
        },
        function (returnData) {
            console.log(returnData);
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