
var app = app || {};

$(function () {
    $("#sendMessage").click(function(evt) {
        var msg = $("#message").val();

        $.ajax({
            type: "POST",
            url: "sendMessage",
            contentType: "application/json",
            data: msg
        }).done(function(data){
            console.log(data);
            $('#message').val('')
            //alert(data);

        }).fail(function(data){
            alert("Error");
        });
    });
});