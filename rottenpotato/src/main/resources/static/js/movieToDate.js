function getMovie(movieId){
    window.location.href="/m/"+movieId;
}
function getNextPage(page) {
    var next= page - '0' + 1;
    window.location.href=next;
}

function getPrevPage(page) {
    var prev= page - '0' - 1;
    window.location.href=prev;
}
function errorImg(img){
    img.src="http://via.placeholder.com/150x200";
    img.onerror = null;
}