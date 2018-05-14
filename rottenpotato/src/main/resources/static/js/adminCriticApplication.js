function deleteApplication(userId){
    $.post("/1/admin/rejectCriticApp",
        {"userId":userId},
        function () {
            location.reload()
        },
        "json");
}