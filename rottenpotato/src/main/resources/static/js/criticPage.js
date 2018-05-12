
function getMovie(movieId){
     window.location.href="/m/"+movieId;
 }
 function sortByBest() {
     var url = window.location.pathname + window.location.search;
     var besttUrl = "";
     if (url.indexOf("order") === -1){
         besttUrl = url+ "?order=1"
     }else{
         var index = url.lastIndexOf("=");
         besttUrl=url.substring(0,index+1);
         besttUrl = besttUrl+'1';

     }
     window.location.href=besttUrl;

 }

function sortByWorst() {

    var url = window.location.pathname + window.location.search;
    var worstUrl = "";
    if (url.indexOf("order") === -1){
        worstUrl = url+ "?order=2"
    }else{
        var index = url.lastIndexOf("=");
        worstUrl=url.substring(0,index+1);
        worstUrl = worstUrl+'2';
    }
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