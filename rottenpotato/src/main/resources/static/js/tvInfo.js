function getActor(actorKey){
    window.location.href="/c/"+actorKey;
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