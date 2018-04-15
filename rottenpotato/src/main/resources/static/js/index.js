function addMovie()
{
   $.post("/movie/addMovie",
        {
        "name" : $("#nameOfAddMovie").val(),
        "date" : $("#dateOfAddMovie").val(),
        "rate" : $("#rateOfAddMovie").val(),
        "boxOffice" : $("#boxOfficeOfAddMovie").val()},
       function (returnData)
       {
           console.log(returnData);
       });
}