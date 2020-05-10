function sendDto() {
    var title = document.getElementById("exampleFormControlInput1").value;
    var list = document.getElementsByClassName('user');
    var users = [];
    for (var i = 0; i < list.length; i++) {
        users[i] = list[i].value;
    }
    let body;
    body = {
        title: title,
        users: users
    };
    $.ajax({
        url: "/newChat",
        method: "POST",
        data: JSON.stringify(body),
        contentType: 'application/json',
        dataType: "json",
        complete: function () {
            window.location.href = '/chats';
        }
    });
}