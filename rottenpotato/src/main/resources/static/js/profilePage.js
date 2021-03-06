function getUser(key) {
    window.location.href="/u/"+key;
}

function getMovie(movieId){
     window.location.href="/m/"+movieId;
 }

function deleteFromWantToSee(itemKey)
{
    $.post("/1/removeWantToSee",
        {"itemKey":itemKey},
        function (message)
        {
            $('#generalModalHeader').html("Success");
            $('#generalModalBody').html('You have deleted from Want to See list review successfully!');
            $('#generalModalCloseButton').click(function () {
                location.reload();
            });
            $('#generalModal').modal('show');
        },
        "json");
}

function deleteFromNotInterested(itemKey)
{
    $.post("/1/removeNotInterested",
        {"itemKey":itemKey},
        function (message)
        {
            $('#generalModalHeader').html("Success");
            $('#generalModalBody').html('You have deleted from Not Interested list successfully!');
            $('#generalModalCloseButton').click(function () {
                location.reload();
            });
            $('#generalModal').modal('show');

        },
        "json");
}

function getItem(url) {
    window.location.href=url;
}

function deleteReview(reviewId , itemKey){
    var successCount = 0;
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


}
// function editReview(reviewId,ratingId) {
//     window.location.href="/1/editReviewPage?reviewId="+reviewId+"&ratingId="+ratingId;
// }
function editReview(reviewId,itemKey,content,rate) {
    console.log('11111');
    $('#editReviewScore').val(rate);
    $('#editReviewContent').html(content);
    $('#editReviewlModal').modal('show');
    $('#editReviewModalConfirmButton').click(function () {
        var score = $('#editReviewScore').val();
        var newContent = $('#editReviewContent').val();
        $.post("/1/editRating",
            {"itemKey":itemKey,"rating":score},
            function (message) {
                $.post("/1/editReview",
                    {"reviewId":reviewId,"content":newContent},
                    function (message) {
                        $('#generalModalHeader').html("Success");
                        $('#generalModalBody').html("You have edited your review successfully!");
                        $('#generalModal').modal('show');
                        $('#generalModalCloseButton').click(function () {
                            location.reload();
                            }
                        );
                    },
                    "json");
            },
            "json");

    });
}

