$(document).ready(function () {

    $("#registration").submit(function (e) {
        e.preventDefault();
        var data={
            username: $('#username').val(),
            password: $('#password').val()
        };
        if (data.password.length<=7){
            $("#mess").html('<b>not a long enough password</b>');
        } else if (!data.password||!data.username) {
            $("#mess").html('<b>BadAss</b>');
        }else if (data.password !== $('#passwordrepeate').val()){
            $("#mess").html('<b>BadAss</b>');
        }
        else{

            $.ajax({
                url:'/registration',
                type:'POST',
                data: JSON.stringify(data),
                contentType:'application/json',
                statusCode:{
                    409:function () {
                        $("#mess").html('<b>Логин занят</b>');
                    },
                    200:function(){
                        window.location.replace('/')
                    }
                }
            });
        }
    });
});