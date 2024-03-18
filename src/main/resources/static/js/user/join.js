$(document).ready(function() {
    $('#join').click(function() {
        $.ajax({
            url : "/user/join",
            type : "post",
            data : {
                "id" : $('#joinId').val(),
                "password" : $('#joinPassword').val(),
                "name" : $('#joinName').val(),
                "email" : $('#joinEmail').val()
            },
            success : function(data) {
                console.log(data);
            }
        })
    })
})