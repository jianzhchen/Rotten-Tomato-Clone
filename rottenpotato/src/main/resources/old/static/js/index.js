function addMovie()
{
    if($("#nameOfAddMovie").val() == "" ||$("#dateOfAddMovie").val() == ""
       ||$("#rateOfAddMovie").val() == "" || $("#boxOfficeOfAddMovie").val() == "")
      {
            alert("Fail to add movie : none of the field can be empty!");
            return;
      }

   $.post("/movie/addMovie",
        {
        "name" : $("#nameOfAddMovie").val(),
        "date" : $("#dateOfAddMovie").val(),
        "rate" : $("#rateOfAddMovie").val(),
        "boxOffice" : $("#boxOfficeOfAddMovie").val()
        },
       function (returnData)
       {
            if (returnData === 'SUCCESS')
            {
                //Movie has been successfully added to repository in service by controller, display success message
                alert("Movie "+$("#nameOfAddMovie").val()+" has been added successfully!");
            }
            else
            {
                alert("Fail to add movie: " +$("#nameOfAddMovie").val());
            }
            console.log(returnData);
       });
}



function signupPost() {
    $.ajax({
        url: "/api0/auth/signup",
        type: "POST",
        data: JSON.stringify({
            "email": $("#signupInputE").val(),
            "username": $("#signupInputN").val(),
            "password": $("#signupInputP1").val(),
            "passwordConfirm": $("#signupInputP2").val(),
            "isCritic": $("#signupInputC").prop('checked')
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (returnData) {
            if (returnData.status === 'ok') {
                $("#signupOk").removeClass("d-none");
            }
            else {
                $("#signupError").removeClass("d-none").text(function (i, origText) {
                    return origText + "\n" + returnData.message;
                });
            }
            console.log(returnData);
        }
    });
}
function loadMoviesOpeningThisWeek() {

    var textBeforeRate = "<a class=\"list-group-item py-0 pt-1 border-0\">" +
        "    <div class=\"container row px-0 mx-0\">" +
        "    <div class=\"'col-1 mx-0\">" +
        "    <i class=\"ico-rank1\">";
    var textBtwRateAndName = "%</i>" +
        "    </div>\n" +
        "    <div class='col-md-7 mx-0'>" +
        "    <p>";

    var textBtwNameAndDate = "</p>" +
        "</div>\n" +
        "<div class=\"'col-md-1 mx-0\">" +
        "    <p>";

    var last = "</p>" +
        "</div>" +
        "</div>" +
        "</a>";

    var list1 = document.getElementById("moviesOpeningThisWeek");
    var context="";

    $.get("/movie/getMoviesOpeningThisWeek",function (returndata) {
        console.log(returndata);
        for(var i=0;i<returndata.length;i++){
            context=context+textBeforeRate;
            context=context+returndata[i].rate;
            context=context+textBtwRateAndName;
            context=context+returndata[i].movieName;
            context=context+textBtwNameAndDate;
            context=context+returndata[i].movieDate.substring(6,10);
            context=context+last;
        }
        list1.innerHTML=context;
        returndata.clear;

    });
}
function loadTopBoxOffice() {
    var textBeforeRate = "<a class=\"list-group-item py-0 pt-1 border-0\">" +
        "    <div class=\"container row px-0 mx-0\">" +
        "    <div class=\"'col-1 mx-0\">" +
        "    <i class=\"ico-rank1\">";
    var textBtwRateAndName = "%</i>" +
        "    </div>\n" +
        "    <div class='col-md-7 mx-0'>" +
        "    <p>";
    var textBtwNameAndBoxOffice = "</p>" +
        "</div>\n" +
        "<div class=\"'col-md-1 mx-0\">" +
        "    <p>$";
    var last = "</p>" +
        "</div>" +
        "</div>" +
        "</a>";
    var list2 = document.getElementById("topBoxOffice");
    var context="";

    $.get("/movie/getTopTenBoxOffice", function (returndata) {
        console.log(returndata)
        for(var i=0;i<returndata.length;i++){
            context=context+textBeforeRate;
            context=context+returndata[i].rate;
            context=context+textBtwRateAndName;
            context=context+returndata[i].movieName;
            context=context+textBtwNameAndBoxOffice;
            context=context+returndata[i].boxOffice;
            context=context+last;
        }
        list2.innerHTML=context;

    });

}
var testAn = document.getElementById("info");
function loadMovies(){
    $.get("/movie/loadMovies",
    {
    },
    function(returnData){
//        console.log(returnData)
        render(returnData);
        });
}
function  render(data){
    var htmlString = "";
    for(i = 0; i < data.length; i++){
        htmlString += "<p>" + data[i].movieName +"<br>"+data[i].movieDate.substring(0, 10) + "</p>";
    }
    testAn.insertAdjacentHTML('beforeend', htmlString);
}




