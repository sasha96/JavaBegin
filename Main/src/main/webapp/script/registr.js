$(document).ready(function () {
    $("#registration").click(function () {
        if (document.getElementById("password").value === null || document.getElementById("password").value === ""
            || document.getElementById("login").value === null || document.getElementById("login").value === ""
        ) {
            $('.div_result').empty().append("You did not fill all the fields");
        } else if (!document.getElementById("password").value.match(/^\d+$/)) {
            $('#password').val('');
            $('.div_result').empty().append("You password can contains only numbers");
        } else {
            loginServletCall();
        }
    }
    );
});

function loginServletCall() {
    var userLogin = {
        "login": document.getElementById("login").value
    };
    var allDate = {
        "login": document.getElementById("login").value,
        "password": document.getElementById("password").value
    };

    $.get('api/checkDateUser', userLogin, function (data) {
        if (data === "this is free login") {
            $.get('api/createNewUser', allDate, function (data) {
                $('#login').val('');
                $('#password').val('');
                $('.div_result').empty().append("You have successfully registered");
            });
        } else {
            $('.div_result').empty().append("This login is busy, try the other one");
            $('#login').val('');
            $('#password').val('');
        }
    }).fail(function (error) {
        alert(error);
    });
};

function exit() {
    $('#bodyId').load("index.html");
}