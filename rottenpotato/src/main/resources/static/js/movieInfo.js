$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});
function changeBtn(btn) {
    if (btn.innerText === 'VIEW ALL'){
        btn.innerText="COLLAPSE"
    }else{
        btn.innerText="VIEW ALL"
    }
}
function getActor(actorKey){
    window.location.href="/c/"+actorKey;
}

function getMovieTopCritic(movieKey){
    window.location.href="/m/t/"+movieKey;
}

function getTrailers(movieKey){
    window.location.href="/v/"+movieKey;
}

function getMovie(movieKey){
    window.location.href="/m/"+movieKey;
}

function getCritic(key){
    window.location.href="/critic/" + key;
}
function postReview(itemKey) {
    var score = $("#score").val();
    var content = $("#reviewContent").val()
    score = Number(score);
    if (score === 0) {
        $('#generalModalHeader').html('Error')
        $('#generalModalBody').html("Please select a score to post your review");
        $('#generalModal').modal('show');
    }else{
        $.post("/1/postReview",
            {"itemKey":itemKey,"content":content},
            $.post("/1/postRating",
                {"itemKey":itemKey,"rating":score},
                function (message) {
                    $('#generalModalHeader').html('Success')
                    $('#generalModalBody').html('You review post successfully!');
                    $('#generalModal').modal('show');
                    $('#generalModalCloseButton').click(function () {
                        location.reload();
                    })
                },
                "json"),
            "json");
    }

}

function addWantToSee(itemKey) {
    $.post("/1/addWantToSee",
        {"itemKey":itemKey},
        function (message) {
            $('#generalModalHeader').html('Success')
            $('#generalModalBody').html('Add to Want To See list successfully!');
            $('#generalModal').modal('show');
        },
        "json");
}

function addNotInterested(itemKey) {
    $.post("/1/addNotInterested",
        {"itemKey":itemKey},
        function (message) {
            $('#generalModalHeader').html('Success')
            $('#generalModalBody').html('Add to Not Interested list successfully!');
            $('#generalModal').modal('show');
        },
        "json");
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
function deleteReview(reviewId,itemKey) {
    $('#confirmModalHeader').html('Delete Review');
    $('#confirmModalBody').html('You really want to delete this review?');
    $('#confirmModal').modal('show');
    $('#confirmModalConfirmButton').click(function () {
        $.post("/1/deleteReview",
            {"reviewId":reviewId},
            function (message) {
                $.post("/1/deleteRating",
                    {"itemKey":itemKey},
                    function (message) {
                        $('#generalModalHeader').html("Success");
                        $('#generalModalBody').html('You have deleted the review successfully!');
                        $('#generalModalCloseButton').click(function () {
                            location.reload();
                        });
                        $('#generalModal').modal('show');
                    },
                    "json");
            },
            "json");
    });
}


function deleteMovie(movieKey) {
    $('#confirmModalHeader').html('Delete Page');
    $('#confirmModalBody').html('You really want to delete this page?');
    $('#confirmModal').modal('show');
    $('#confirmModalConfirmButton').click(function () {
        $.post("/1/admin/delete",
            {"key":movieKey},
            function () {
                $('#generalModalHeader').html('Success');
                $('#generalModalBody').html('You have delete this page successfully! Go to homepage now...');
                $('#generalModal').modal('show');
                $('#generalModalCloseButton').click(function () {
                    window.location.href="/";
                })
            },
            "json");
    });
}