function changeEmail() {
    var newEmail = $("#ce_new_email").val();
    var ps = $("#ce_password").val();
    $.post("/1/changeEmail",
        {"newEmail" : newEmail, "password" : ps},
        function (message) {
            console.log(message);
            $('#generalModalBody').html('Change email successfully! Log out now...');
            $('#generalModal').modal('show');
            $('#generalModalCloseButton').click(function () {
                window.location.href="/login";
            });
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
            $('#generalModalBody').html('Change password successfully!');
            $('#generalModal').modal('show');
        },
        "json")
}

function editNames() {
    var newFirstName = $("#ce_new_first_name").val();
    var newLastName = $("#ce_new_last_name").val();
    $.post("/1/editNames",
        {"newFirstName" : newFirstName, "newLastName" : newLastName},
        function (message) {
            console.log(message);
            $('#generalModalBody').html('Change name successfully!');
            $('#generalModal').modal('show');
            $('#generalModalCloseButton').click(function () {
                location.reload();
            });
        },
        "json")
}
function changeToOpen() {
    $.post("/1/changePrivacy",
        {"openProfile":"true"},
        function () {
            $('#generalModalBody').html('Change privacy setting successfully!');
            $('#generalModal').modal('show');
            $('#generalModalCloseButton').click(function () {
                location.reload();
            });
        },
        "json");
}
function changeToHide() {
    $.post("/1/changePrivacy",
        {"openProfile":"false"},
        function () {
            $('#generalModalBody').html('Change privacy setting successfully!');
            $('#generalModal').modal('show');
            $('#generalModalCloseButton').click(function () {
                location.reload();
            });
        },
        "json");
}

function criticApplication() {
    $('#criticApplicationModal').modal('show');
    $('#criticApplicationConfirmButton').click(function () {
        var reason = $('#criticApplicationReason').val();
        $.post('/1/criticApplication',
            {"content":reason},
            function (message) {
                $('#generalModalBody').html('We have received your application');
                $('#generalModal').modal('show');
            },
            "json");
    });
}