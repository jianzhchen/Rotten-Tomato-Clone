function report(reviewId,itemKey)
{
    var content = $("#reportContent").val();

    $.post("/1/reportReview",
        {"reviewId":reviewId,"content":content},
        function (message)
        {
                alert(message.message);
        },
        "json");
}

function reportReview(reviewId)
{
    window.location.href="/1/reportReview?reviewId="+reviewId;
}