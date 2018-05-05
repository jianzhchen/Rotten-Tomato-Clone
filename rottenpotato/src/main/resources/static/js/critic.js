function getCritic(key){
    window.location.href="/critic/"+key;
}

function getNextPage(page) {
    var next= page - '0' + 1;
    window.location.href=next;
}

function getPrevPage(page) {
    var prev= page - '0' - 1;
    window.location.href=prev;
}