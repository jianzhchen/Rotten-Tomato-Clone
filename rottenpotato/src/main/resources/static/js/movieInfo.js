function getActor(actorKey){
    window.location.href="/c/"+actorKey;
}

function getTrailers(movieKey){
    window.location.href="/v/"+movieKey;
}

function getCritic(key){
    window.location.href="/critic/" + key;
}
function postReview(itemKey) {
    var score = $("#score").val();
    var content = $("#reviewContent").val()
    score = Number(score);
    if (score === 0) {
        alert("Please select a score to post your review");
    }else{
        $.post("/1/postReview",
            {"itemKey":itemKey,"content":content},
            function (message) {
                console.log(message);
                alert(message.status);
            },
            "json");
        $.post("/1/postRating",
            {"itemKey":itemKey,"rating":score},
            function (message) {
                console.log(message);
                alert(message.status);
            },
            "json");

    }

}
function loginMessage() {
    alert("Please login first");
}

function addWantToSee(itemKey) {
    $.post("/1/addWantToSee",
        {"itemKey":itemKey},
        function (message) {
            console.log(message);
            alert(message.status);
        },
        "json");
}

function addNotInterested(itemKey) {
    $.post("/1/addNotInterested",
        {"itemKey":itemKey},
        function (message) {
            console.log(message);
            alert(message.status);
        },
        "json");
}

function getUser(key) {
    window.location.href="/u/"+key;
}