<script type="text/javascript">
    function addMovie()
    {
        var nameOfAddMovie = document.getElementById("nameOfAddMovie").value;
        var videosOfAddMovie = document.getElementById("videosOfAddMovie").value;
        var photosOfAddMovie = document.getElementById("photosOfAddMovie").value;

        document.cookie="userID="+$("#reg_id").val();
        document.cookie="userPassword="+$("#reg_password").val();
        document.cookie="role="+ $("#selectReg").val();

        $.post("/customers/insertCustomers",
        { "id" : $("#reg_id").val(),
            "password" :  $("#reg_password").val(),
            "lname" : $("#reg_lname").val(),
            "fname":$("#reg_fname").val(),
            "phone":$("#reg_phone").val(),
            "address":$("#reg_address").val(),
            "email" : $("#reg_email").val()} ,
        function(returndata)
        {
            console.log(returndata);
            if(returndata.msg=="add success")
            {
                window.location.href="/customersHomePage.html"
            }
            else
            {
                window.location.href="/loginfail.html"
            }
         });

    }

</script>