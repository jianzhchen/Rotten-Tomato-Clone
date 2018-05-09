function getUser(key) {
    window.location.href="/u/"+key;
}

function follow(userId){
    $.post("/1/follow",
    {"userId":userId},
        function (message)
        {
            console.log(message);
            alert(message.status);
        },
        "json");
}

function unFollow(userId){
    $.post("/1/unFollow",
    {"userId":userId},
        function (message)
        {
            console.log(message);
            alert(message.status);
        },
        "json");
}

function toggleFollowUnFollow(userId)
{
    var change = document.getElementById("toggleFollowUnFollow");
    if (change.innerHTML == "Follow")
    {
        $.post("/1/follow",
            {"userId":userId},
                function (message)
                {
                    console.log(message);
                    alert(message.status);
                },
                "json");
        change.innerHTML = "UnFollow";
        change.classList.remove("btn-success")
        change.classList.add("btn-danger");
    }
    else
    {
        if (confirm("You Really Want To UnFollow?"))
        {
            $.post("/1/unFollow",
                {"userId":userId},
                    function (message)
                    {
                        console.log(message);
                        alert(message.status);
                    },
                    "json");
            change.innerHTML = "Follow";
            change.classList.remove("btn-danger")
            change.classList.add("btn-success");
        }
    }
}
