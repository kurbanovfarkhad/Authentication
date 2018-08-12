$(document).ready(function () {
    $("#tobasket").click(function (e) {
        e.preventDefault();
            console.log("Hello");
        var data = {
            book_id: $("#tobasket").attr("name")
        };
        console.log(typeof(parseInt(data.book_id)));
        $.ajax({
            url: '/addtobasket',
            type: 'POST',
            data:  JSON.stringify(data),
            contentType:'application/json',
            statusCode: {
                200: function () {
                    console.log("successful")
                }
            }
        });
    });

    $("#ok").click(function (e) {
        e.preventDefault();

        $.ajax({
            url: '/buybasket',
            type: 'POST',
            statusCode: {
                200: function () {
                    console.log("successful")
                }
            }
        });
    });

    $("#deleteFromBasket").click(function (e) {
        e.preventDefault();
        console.log("Hello");
        var data = {
            book_id: $("#deleteFromBasket").attr("name")
        };
        console.log(typeof(parseInt(data.book_id)));
        $.ajax({
            url: '/deleteFromBasket',
            type: 'POST',
            data:  JSON.stringify(data),
            contentType:'application/json',
            statusCode: {
                200: function () {
                    console.log("successful")
                }
            }
        });
    });

})
;