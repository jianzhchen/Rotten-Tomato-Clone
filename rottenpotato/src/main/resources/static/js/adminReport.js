function approveReport(reportId, reviewId,itemKey) {
    $.post("/1/deleteReview",
        {"reviewId":reviewId},
        function () {
            $.post("/1/deleteRating",
                {"itemKey":itemKey},
                function () {
                },
                "json")
        },
        "json")
    $.post("/1/deleteReport",
        {"reportId":reportId},
        function () {
            location.reload();
        },
        "json");
}

function deleteReport(reportId) {
    $.post("/1/deleteReport",
        {"reportId":reportId},
        function () {
            location.reload();
        },
        "json");
}