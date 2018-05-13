function getMovie(movieId){
     window.location.href="/m/"+movieId;
 }

function getTv(tvId){
    window.location.href="/t/"+tvId;
}

function getActor(CeleId){
    window.location.href="/c/"+CeleId;

}
function getPrevPage(page) {
    var url = window.location.pathname + window.location.search;
    var prev= Number(page) - 1;
    var index = url.lastIndexOf("page");
    var prevUrl=url.substring(0,index+5);
    prevUrl = prevUrl+prev;
    window.location.href=prevUrl;
}
function getNextPage(page) {
    var url = window.location.pathname + window.location.search;
    var nextUrl = "";
    if (url.indexOf("page") === -1){
        nextUrl = url+ "&page=1"
    }else{
        var next= Number(page)  + 1;
        var index = url.lastIndexOf("page");
        nextUrl=url.substring(0,index+5);
        nextUrl = nextUrl+next;

    }
    window.location.href=nextUrl;
}
function errorImg(img){
    img.src="http://via.placeholder.com/240x135";
    img.onerror = null;
}
function changeToGrid(page) {
    var url = window.location.pathname + window.location.search;
    var newUrl = ""
    if(url.indexOf("style")===-1 && url.indexOf("page")===-1){
        newUrl = url+"&style=grid&page=0";
    }else if(url.indexOf("style") === -1){
        var index = url.lastIndexOf("page");
        newUrl = url.substring(0,index);
        newUrl = newUrl + "style=grid&page="+page;
    }else {
        var index = url.lastIndexOf("style");
        newUrl = url.substring(0,index);
        newUrl = newUrl + "style=grid&page="+page;
    }
    window.location.href=newUrl;
}

function changeToList(page) {
    var url = window.location.pathname + window.location.search;
    var newUrl = ""
    if(url.indexOf("style")===-1 && url.indexOf("page")===-1){
        newUrl = url+"&style=list&page=0";
    }else if(url.indexOf("style") === -1){
        var index = url.lastIndexOf("page");
        newUrl = url.substring(0,index);
        newUrl = newUrl + "style=list&page="+page;
    }else {
        var index = url.lastIndexOf("style");
        newUrl = url.substring(0,index);
        newUrl = newUrl + "style=list&page="+page;
    }
    window.location.href=newUrl;
}