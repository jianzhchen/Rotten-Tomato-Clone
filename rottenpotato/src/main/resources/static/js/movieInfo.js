function getActor(actorKey){
    window.location.href="/c/"+actorKey;
}

function getCritic(key){
    window.location.href="/critic/" + key;
}
function postReview(movieKey) {
    var score = $("#score").val();
    score = Number(score);
    if (score === 0) {
        alert("Please select a score to post your review");
    }else{
        alert("Your score is "+score);
    }

}