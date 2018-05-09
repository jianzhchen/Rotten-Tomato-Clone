function changeEmail() {
    var newEmail = $("#ce_new_email").val();
    var ps = $("#ce_password").val();
    $.post("/1/changeEmail",
        {"newEmail" : newEmail, "password" : ps},
        function (message) {
            console.log(message);
            if(message.status === "OK"){
                window.location.href="/login";
            }else{
                alert(message.message);
            }


        },
        "json")
}

function changePassword() {
    var newPs = $("#cp_new_password").val();
    var oldPs = $("#cp_old_password").val();
    $.post("/1/changePassword",
        {"newPassword" : newPs, "password" : oldPs},
        function (message) {
            alert(message.message);
            console.log(message);

        },
        "json")
}