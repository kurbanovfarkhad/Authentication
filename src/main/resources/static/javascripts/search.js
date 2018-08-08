$(document).ready(function () {
    $("#search").submit(function (e) {
        //e.preventDefault();
        var data = {
            text: $("#text").val()
        };
        console.log(data);
        $.ajax({
            url: '/search',
            type: 'GET',
            data: data,
            contentType: 'application/json'
            // ,
            // success:function (data) {
            //     console.log(data);
            //         var a = data.length;
            //         var code = "<ul>";
            //         while(a>=1){
            //             console.log(data[a-1].id);
            //             code = code +"<ul>"
            //                             + "<li>"+data[a-1].title+"</li>"
            //                             + "<li>"+data[a-1].description+"</li>"
            //                             + "<li>"+"<img src='/img/"+data[a-1].picture +"' alt='pic'>"+"</li>"
            //                             + "<li>"+"<a href='/edit?id='>edit</a>"+" "+"<a href='/delete?id="+data[a-1].id+"'>delete</a>"+"</li>"
            //                         +"</ul>"
            //
            //             a--;
            //         }
            //         code = code+'</ul>';
            //         $("#body").html(code);
            //
            // }
        });
    });
})