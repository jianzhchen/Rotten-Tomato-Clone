function getCritic(key){
    window.location.href="/critic/" + key;
}

function getNextPage(page) {
    var next= Number(page) + 1;
    window.location.href=next;
}

function getPrevPage(page) {
    var prev= Number(page) - 1;
    window.location.href=prev;
}

function errorImg(img){
    img.src="http://via.placeholder.com/100x100";
    img.onerror = null;
}