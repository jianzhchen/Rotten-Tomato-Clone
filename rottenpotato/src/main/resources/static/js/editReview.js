function edit(reviewId,itemKey) {
    var successCount = 0;
    var content = $("#reviewContent").val();
    var score = $("#score").val();
    $.post("/1/editRating",
        {"itemKey":itemKey,"rating":score},
        function (message) {
            if (message.status === "ok"){
                successCount = successCount+1;
            }else {
                alert(message.message);
            }
        },
        "json");
    $.post("/1/editReview",
        {"reviewId":reviewId,"content":content},
        function (message) {
            if (message.status === "ok"){
                successCount = successCount +1;
            }else {
                alert(message.message);
            }

        },
        "json");
    if(successCount === 2){
        alert("Update successfully!")
        window.location.href="/1/me"
    }
}