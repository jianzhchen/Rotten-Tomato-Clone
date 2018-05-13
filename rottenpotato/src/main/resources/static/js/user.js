$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});
function toggleFollowUnFollow(userId)
{

    var change = document.getElementById("toggleFollowUnFollow");
    if (change.innerText === "FOLLOW")
    {
        $.post("/1/follow",
            {"userId":userId},
            function (message)
            {
                location.reload();
            },
            "json");
        change.innerText = "UNFOLLOW";
        change.classList.remove("btn-success")
        change.classList.add("btn-danger");
    }
    else
    {
        $('#confirmModalHeader').html('Confirm');
        $('#confirmModalBody').html('You Really Want To UNFOLLOW?');
        $('#confirmModal').modal('show');
        $('#confirmModalConfirmButton').click(function () {
            $.post("/1/unFollow",
                {"userId":userId},
                function (message)
                {
                    location.reload();
                },
                "json");
            change.innerText = "FOLLOW";
            change.classList.remove("btn-danger");
            change.classList.add("btn-success");
        });
    }
}

function getUser(key) {
    window.location.href="/u/"+key;
}

function reportReview(reviewId) {
    $('#reportModal').modal('show');
    $('#reportModalConfirmButton').click(function () {
        var reason = $('#reportReason').val();
        $.post("/1/reportReview",
            {"reviewId":reviewId, "content": reason},
            function (message) {
                $('#generalModalHeader').html('Success');
                $('#generalModalBody').html('You have sent the report successfully!');
                $('#generalModal').modal('show');
            },
            "json");
    });
}

function reportUser(userId) {
    $('#reportModal').modal('show');
    $('#reportModalConfirmButton').click(function () {
        var reason = $('#reportReason').val();
        $.post("/1/reportUser",
            {"userId":userId, "content": reason},
            function (message) {
                $('#generalModalHeader').html('Success');
                $('#generalModalBody').html('You have sent the report successfully!');
                $('#generalModal').modal('show');
            },
            "json");
    });
}