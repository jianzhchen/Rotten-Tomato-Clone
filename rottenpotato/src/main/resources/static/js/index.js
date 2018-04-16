function addMovie()
{
    if($("#nameOfAddMovie").val() == "" ||$("#dateOfAddMovie").val() == ""
       ||$("#rateOfAddMovie").val() == "" || $("#boxOfficeOfAddMovie").val() == "")
      {
            //Movie is successfully added to repository in service by controller, display success message
             alert("Fail to add movie "+$("#nameOfAddMovie").val());
            return;
      }
      else
      {
           //Movie is not successfully added to repository in service by controller, display error message
            alert("Movie "+$("#nameOfAddMovie").val()+" has been added successfully!");
      }
   var error = true;
   $.post("/movie/addMovie",
        {
        "name" : $("#nameOfAddMovie").val(),
        "date" : $("#dateOfAddMovie").val(),
        "rate" : $("#rateOfAddMovie").val(),
        "boxOffice" : $("#boxOfficeOfAddMovie").val()
        },
       function (returnData)
       {
       });


}