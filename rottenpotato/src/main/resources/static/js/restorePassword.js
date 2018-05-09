function restorePassword(token) {
    var email = $("#email").val();
    var newPassword = $("#newPassword").val();
    $.post("/restorePassword",
        {"email":email, "token":token, "newPassword":newPassword},
        function (message) {
            if(message.status === "ok"){
                window.location.href="/loginPage";
            }else{
                alert(message.message);
            }

        },
        "json");

}