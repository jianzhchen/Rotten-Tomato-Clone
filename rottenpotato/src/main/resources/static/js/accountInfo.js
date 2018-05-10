function changeEmail() {
    var newEmail = $("#ce_new_email").val();
    var ps = $("#ce_password").val();
    $.post("/1/changeEmail",
        {"newEmail" : newEmail, "password" : ps},
        function (message) {
            console.log(message);
            if(message.status === "ok"){
                alert("Change email successfully! Logout now...")
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
            console.log(message);
            if(message.status === "ok"){
                alert("Change password successfully")
            }else {
                alert(message.message);
            }
        },
        "json")
}