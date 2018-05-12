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
         besttUrl = nextUrl+'1';

     }
     window.location.href=besttUrl;
     $('#sortBest').removeClass("btn-secondary")
     $('#sortBest').addClass("btn-primary")
     $('#sortWorst').removeClass("btn-primary")
     $('#sortWorst').addClass("btn-secondary")



 }

function sortByWorst() {

    var url = window.location.pathname + window.location.search;
    var worstUrl = "";
    if (url.indexOf("order") === -1){
        worstUrl = url+ "?order=2"
    }else{
        var index = url.lastIndexOf("=");
        worstUrl=url.substring(0,index+1);
        worstUrl = nextUrl+'2';

    }
    window.location.href=worstUrl;
    $('#sortBest').removeClass("btn-primary")
    $('#sortBest').addClass("btn-secondary")
    $('#sortWorst').removeClass("btn-secondary")
    $('#sortWorst').addClass("btn-primary")
}