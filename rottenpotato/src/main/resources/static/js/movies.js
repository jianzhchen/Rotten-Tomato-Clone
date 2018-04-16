function init(){
    var hardCodeMovies = document.getElementById("topBoxOffice");
    var rowHead = "<div class=\"row m-5\">";
    var rowtail = "</div>";

    var movieTextBeforeName = "<div class=\"col-md-3 \">" +
        "    <a href=\"https://placeholder.com\"><img class=\"img-center\" src=\"http://via.placeholder.com/130x193.jpg\"></a>" +
        "    <p class=\"font-weight-bold text-md-center my-0\">";
    var textBtwNameAndRate = "</p>" +
        "    <i class=\"ico-rank3 mx-auto\">";
    var textBtwNameAndTime = "</i>" +
        "    <p class=\"text-md-center\">";
    var last ="</p>" +
        "</div>";

    var replaceContext="";

    $.get("/movie/getTopBoxOffice",function (returndata) {
        console.log(returndata);
        for(var row = 0 ; row<3 ; row++){
            replaceContext=replaceContext+rowHead;
            for (var col=0 ; col<4 ; col++){
                replaceContext=replaceContext+movieTextBeforeName;
                replaceContext=replaceContext+returndata[row*3+col].movieName;
                replaceContext=replaceContext+textBtwNameAndRate;
                replaceContext=replaceContext+returndata[row*3+col].rate+"%";
                replaceContext=replaceContext+textBtwNameAndTime;
                replaceContext=replaceContext+returndata[row*3+col].movieDate.substring(6,10);
                replaceContext=replaceContext+last;
            }
            replaceContext=replaceContext+rowtail
        }
        hardCodeMovies.innerHTML=replaceContext;
    })

}
