function report(reviewId)
{
//    var content = $("#reportContent").val();
    var content = document.getElementById("reportContent").value();

    $.post("/1/reportReview",
        {"reviewId":reviewId,"content":"content for report"},
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