function forgotPassword() {
    var email = $("#fp_email").val();
    $.post("/forgotPassword",
        {"email":email},
        function (message) {
            $('#generalModalBody').html('Check your email to restore your password!');
            $('#generalModal').modal('html');
        },
        "json");
}

function verifyResend() {
    var email = $("#c_email").val();
    $.post("/verifyResend",
        {"email":email},
        function (message) {
        console.log(message);
            $('#generalModalBody').html('Resend verify email successfully!');
            $('#generalModal').modal('html');
        },
        "json");
}