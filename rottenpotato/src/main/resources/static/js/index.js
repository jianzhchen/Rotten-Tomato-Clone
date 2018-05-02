function getMovie(movieId){
    $.get("/m/"+movieId,function (returndate) {
        console.log(returndate)
    })
}

function getTv(TvId){
    $.get("/m/"+TvId,function (returndate) {
        console.log(returndate)
    })
}