function getActor(actorKey){
    window.location.href="/c/"+actorKey;
}

function getCritic(key){
    window.location.href="/critic/" + key;
}
function postReview(movieKey) {
    var score = $("#score").val();
    var content = $("#reviewContent").val()
    score = Number(score);
    if (score === 0) {
        alert("Please select a score to post your review");
    }else{
        $.post("/1/postReview",
            {"itemKey":movieKey,"content":content},
            function (message) {
                console.log(message);
                alert(message);
            })
        $.post("/1/postRating",
            {"itemKey":movieKey,"rating":score},
            function (message) {
                console.log(message);
                alert(message);
            })

    }

}
function loginMessage() {
    alert("Please login first to post review");
}