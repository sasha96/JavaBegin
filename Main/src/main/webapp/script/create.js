$(document).ready(function () {
    if (document.cookie==""){
        exit();
    }
});

function clickOnButton() {
    var chapterDate = {
        "chapterName": document.getElementById("menu").value,
        "nameFile": document.getElementById("name_file").value,
        "chapterText": document.getElementById("text").value,
        "nameSubChapters": document.getElementById("name_subChapters").value
    };
    $.get('/api/createFile', chapterDate, function (data) {
        if (data === "") {
            alert("You don't have access to create new file");
            $('#bodyId').load("index.html");
        }
        else if (true) {
            // $('.result').empty().append("The file successfully save ");
            // $('#bodyId').load("head.html");
            $('#name_file').val('');
            $('#text').val('');
            $('#name_subChapters').val('');
            $('.div_result').empty().append("The file successfully save ");
        } else {
            $('.div_result').empty().append("The file have error ");
        }
    });
}

function exit() {
    $('#bodyId').load("head.html");
}