$(document).ready(function () {

    $("#edit").submit(function (e) {
        e.preventDefault();
        if (!$("#title").val()) {
            console.log("you must send this place");
        }else if (!$("#description").val()) {
            console.log("you must send this place");
        }else {
            var data = new FormData(this);
            data.append('csrfParameter', token);
            $.ajax({
                url: $("#edit").attr("action"),
                type: 'POST',
                data: /*new FormData( this )*/ data,
                processData: false,
                contentType: false,
                statusCode: {
                    200: function () {
                        console.log("successfull")
                    }
                }
            });
        }
    });
});