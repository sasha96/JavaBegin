$(document).ready(function () {
    deleteCookie();
    $("#button").click(function () {
        if (document.getElementById("password").value === null || document.getElementById("password").value === ""
            || document.getElementById("login").value === null || document.getElementById("login").value === "" || !isFinite(document.getElementById("password").value)) {
            $('.mydiv').empty().append('Please input login or password correct');
        } else {
            loginServletCall();
            setCookie(document.getElementById("password").value, document.getElementById("login").value, 6000);
        }
    });
});

function loginServletCall() {
    var userDate = {
        "login": document.getElementById("login").value,
        "password": document.getElementById("password").value
    };

    var userDataJson = JSON.stringify(userDate);

    $.ajax({
        type: "POST",
        url: 'api/login',
        data: userDataJson,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: [function (data) {//this is work when dataType : "text"
            if (data.responseText === "Your login or password have some error please write again") {
                $('.mydiv').empty().append(data);
            } else {
                $('.mydiv').empty().append(data);
                if (data === "This is logged admin") {
                    $('#bodyId').load("head.html");
                }
                else if (data === "This is logged user") {
                    $('#bodyId').load("user.html");
                }
            }
        }],
        error: function (data) {//this is work when dataType :" json"
            if (data.responseText === "Your login or password have some error please write again") {
                $('.mydiv').empty().append(data);
            } else {
                $('.mydiv').empty().append(data);
                if (data === "This is logged admin") {
                    $('#bodyId').load("head.html");
                }
                else if (data === "This is logged user") {
                    $('#bodyId').load("user.html");
                }
            }
        }
    });
}

function registration() {
    $('#bodyId').load("registration.html");
}

function setCookie(name, value, options) {
    options = options || {};
    var expires = options.expires;
    if (typeof expires == "number" && expires) {
        var data = new Date();
        data.setTime(data.getTime() + expires * 1000);
        expires = options.expires = d;
    }
    if (expires && expires.toUTCString) {
        options.expires = expires.toUTCString();
    }
    var nameEncode = b64EncodeUnicode(name);
    var valueEncode = b64EncodeUnicode(value);
    var updatedCookie = nameEncode + "=" + valueEncode;
    deleteCookie();
    document.cookie = updatedCookie;
}

function b64EncodeUnicode(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function toSolidBytes(match, p1) {
        return String.fromCharCode('0x' + p1);
    }));
}

function deleteCookie() {
    document.cookie.split(";").forEach(function (c) {
        document.cookie = c.replace(/^ +/, "")
            .replace(/=.*/, "=;expires=" + new Date()
                    .toUTCString() + ";path=/");
    });
}