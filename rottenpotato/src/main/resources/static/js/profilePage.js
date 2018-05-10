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
            var x = document.getElementById("wantToSeeAlert");
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
            setTimeout(function(){ location.reload(); }, 1000);
        },
        "json");
}

function deleteFromNotInterested(itemKey)
{
    $.post("/1/removeNotInterested",
        {"itemKey":itemKey},
        function (message)
        {
            var x = document.getElementById("notInterestedAlert");
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
            setTimeout(function(){ location.reload(); }, 1000);

        },
        "json");
}


function toggleFollowUnFollow(userId)
{

    var change = document.getElementById("toggleFollowUnFollow");
    if (change.innerText === "FOLLOW")
    {
        $.post("/1/follow",
            {"userId":userId},
                function (message)
                {
                    location.reload();
                },
                "json");
        change.innerText = "UNFOLLOW";
        change.classList.remove("btn-success")
        change.classList.add("btn-danger");
    }
    else
    {
        if (confirm("You Really Want To UNFOLLOW?"))
        {
            $.post("/1/unFollow",
                {"userId":userId},
                    function (message)
                    {
                        location.reload();
                    },
                    "json");
            change.innerText = "FOLLOW";
            change.classList.remove("btn-danger")
            change.classList.add("btn-success");
        }
    }
}
function getItem(url) {
    window.location.href=url;
}

function deleteReview(reviewId , itemKey){
    var successCount = 0;
    $.post("/1/deleteReview",
        {"reviewId":reviewId},
        function (message) {
            if(message.status === "ok"){
                successCount = successCount+1;
            }else {
                alert(message.message);
            }

        },
        "json");

    $.post("/1/deleteRating",
        {"itemKey":itemKey},
        function (message) {
            if(message.status === "ok"){
                successCount = successCount+1;

            }else {
                alert(message.message);
            }
        },
        "json");
    if(successCount === 2){
        alert("Delete successfully!")
        window.location.href="/1/me";
    }

}
function editReview(reviewId,ratingId) {
    window.location.href="/1/editReviewPage?reviewId="+reviewId+"&ratingId="+ratingId;
}