$(document).ready(function () {
    if (document.cookie==""){
        exit();
    }
});

$(function () {
    $.get('/api/chapter', function (data) {
        var headerItems = JSON.parse(data);
        for (var i = 0; i < headerItems.length; i++) {
            var variableLi = $('<li></li>');
            variableLi.append('<a href="#">' + headerItems[i].name + '</a>');
            var variableUl = $('<ul item_id=' + headerItems[i].id +
                ' class="animenu__nav__child"></ul>');
            variableLi.append(variableUl);
            $('.animenu__nav').append(variableLi);
            variableLi.mouseover({id: headerItems[i].id}, buildChapterChild);
        }
    });
});

function buildChapterChild(e) {
    var id = e.data.id;
    $.get('/api/subChapter?id=' + id, showChildrenChapters.bind(null, e));
}

function showChildrenChapters(e, data) {
    var subchapters = JSON.parse(data);
    $(e.target).parent().find('.animenu__nav__child').empty();
    for (var i = 0; i < subchapters.length; i++) {
        var varLi = $('<li>' + '<a href="#">' + subchapters[i].name + '</a>' + '</li>');
        $(e.target).parent().find('.animenu__nav__child').append(varLi);
        varLi.on('click', {name: subchapters[i].name}, updateChapter);
    }
}

function updateChapter(event) {
    $('.div_result').empty();
    $.get('api/subChapterByName?name=' + event.data.name, function (data) {
        $('#changedTextSubChapter').empty().append(data.trim());
    });
    var nameChildChapter = event.data;
    $(".updateButton").on("click", nameChildChapter, update);
}

function update(e) {
    var chapterData = {
        "chapterName": e.data.name,
        "changedTextSubChapter": document.getElementById("changedTextSubChapter").value
    };
    $.get('/api/updateSubChapter', chapterData, function (data) {
        if (data === "") {
            alert("You don't have access to update new file");
            $('#bodyId').load("index.html");
        } else {
            $('.div_result').empty().append("The file successfully update ");
            $('#bodyId').load("update.html");
        }
    });
}

function exit() {
    $('#bodyId').load("head.html");
}