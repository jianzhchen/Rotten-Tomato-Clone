function getNextPage(page) {
    var next= Number(page) + 1;
    window.location.href=next;
}

function getPrevPage(page) {
    var prev= Number(page) - 1;
    window.location.href=prev;
}

function getTV(tvId){
    window.location.href="/t/"+tvId;
}

function errorImg(img){
    img.src="http://via.placeholder.com/150x200";
    img.onerror = null;
}