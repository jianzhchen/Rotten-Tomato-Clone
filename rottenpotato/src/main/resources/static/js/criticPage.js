
function getMovie(movieId){
     window.location.href="/m/"+movieId;
 }
 function sortByBest(page) {
     var url = window.location.pathname;
     var besttUrl = url+"?order=1&page="+page;
     window.location.href=besttUrl;

 }

function sortByWorst(page) {
    var url = window.location.pathname;
    var worstUrl = url+"?order=2&page"+page;
    window.location.href=worstUrl;
}
function getPrevPage(page) {
    var url = window.location.pathname + window.location.search;
    var prev= Number(page) - 1;
    var index = url.lastIndexOf("=");
    var prevUrl=url.substring(0,index+1);
    prevUrl = prevUrl+prev;
    window.location.href=prevUrl;
}
function getNextPage(page) {
    var url = window.location.pathname + window.location.search;
    var nextUrl = "";
    if (url.indexOf("page") === -1){
        nextUrl = url+ "?page=1"
    }else{
        var next= Number(page)  + 1;
        var index = url.lastIndexOf("=");
        nextUrl=url.substring(0,index+1);
        nextUrl = nextUrl+next;

    }
    window.location.href=nextUrl;
}