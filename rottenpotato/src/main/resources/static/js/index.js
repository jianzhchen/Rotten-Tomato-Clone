function openMovie(movieId){
    $.get("/m/$movieId","id : $movieId",function (returndate) {
        console.log(returndate)
    })
}

function openTv(TvId){
    $.get("/m/$movieId","id : $movieId",function (returndate) {
        console.log(returndate)
    })
}