function forgotPassword() {
    var email = $("#fp_email").val();
    $.post("/forgotPassword",
        {"email":email},
        function (message) {
            if (message.status === "ok"){
                alert("You can go to your email to restore your password")
            }else {
                alert(message.message);
            }
        },
        "json");
}