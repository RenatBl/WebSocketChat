<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New chat</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/static/js/chatCreate.js"></script>
</head>

<script>
    function clicker() {
        var select = document.getElementById('exampleFormControlInput2');
        if (select.value !== '') {
            document.getElementById('users-list').innerHTML +=
                '<div class="list-group">' +
                '<div class="list-group-item list-group-item-action flex-column align-items-start">' +
                '<div class="d-flex w-100 justify-content-between">' +
                '<input type="hidden" class="user" value="' + select.value + '">' +
                '<h5 class="mb-1">' + $("#exampleFormControlInput2 option:selected").text() + '</h5>' +
                '</div>' +
                '</div>' +
                '</div>';
            select.removeChild(document.getElementById(select.value));
            document.getElementById('send').disabled = false;
        } else {
            document.getElementById('add').disabled = true;
        }
    }
</script>

<style>
    html, body {
        margin: 0;
        padding: 0;
    }
    .clear {
        clear: both;
    }

    .wrapper {
        width: 100%;
        margin-bottom: 20px;
        background: #d4d4d4;
        font-family: 'Raleway', sans-serif;
        font-weight: 400;
        font-size: 14px;
        line-height: 26px;
        color: #666;
    }

    .navigation {
        list-style: none;
        padding: 0;
        margin: 0;
        background: rgb(58, 58, 58);
    }

    .navigation li {
        float: left;
    }

    .navigation li:hover {
        background: #222;
    }

    .navigation li:first-child {
        -webkit-border-radius: 5px 5px 0 0;
        border-radius: 5px 0 0 5px;
    }

    .navigation li a {
        display: block;
        padding: 0 20px;
        text-decoration: none;
        line-height: 40px;
        color: #fff;
    }

    .navigation ul {
        display: none;
        position: absolute;
        list-style: none;
        margin-left: -3px;
        padding: 0;
        overflow: hidden;
    }

    .navigation ul li {
        float: none;
    }

    .navigation li:hover > ul {
        display: block;
        background: #222;
        border: solid 3px #fff;
        border-top: 0;

        -webkit-border-radius: 0 0 8px 8px;
        border-radius: 0 0 8px 8px;

        -webkit-box-shadow: 0px 3px 3px 0px rgba(0, 0, 0, 0.25);
        box-shadow: 0px 3px 3px 0px rgba(0, 0, 0, 0.25);
    }

    .navigation li:hover > ul li:hover {
        -webkit-border-radius: 0 0 5px 5px;
        border-radius: 0 0 5px 5px;
    }

    .navigation li li a:hover {
        background: #000;
    }

    .navigation ul li:last-child a,
    .navigation ul li:last-child a:hover {
        -webkit-border-radius: 0 0 5px 5px;
        border-radius: 0 0 5px 5px;
    }

    .logout {
        float: right !important;
    }
</style>

<body>

<div class="wrapper">
    <ul class="navigation">
        <li><a href="/user" title="Profile">Profile</a></li>
        <li><a href="/users" title="All users">All users</a></li>
        <li><a href="/chats" title="Chats">Chats</a></li>
        <li class="logout"><a href="/logout" title="Logout">Logout</a></li>
        <div class="clear"></div>
    </ul>
</div>

<div>
    <form style="min-height: 500px; width: 60%; margin-left: 10%; margin-top: 4%; border: 2px #d4d4d4 solid; border-radius: 6px">
        <h2 align="center" style="margin-top: 20px">Create new chat</h2>
        <div style="display: inline-block; height: 300px; width: 40%">
            <div class="form-group" style="margin-top: 50px; margin-left: 50px">
                <label for="exampleFormControlInput1" style="margin-left: 32px">Chat title</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Enter chat's name"
                       style="width: 80%; position: inherit; margin-left: 30px" required>
            </div>
            <div class="input-group" style="margin-top: 25px; margin-left: 50px">
                <label for="exampleFormControlInput2" style="margin-left: 32px">Choose chat's participants</label>

                <select class="custom-select" id="exampleFormControlInput2"
                        style="width: 70%; position: inherit; margin-left: 30px">
                    <#list users as user>
                        <#if user.id != owner.id>
                            <option id="${user.id}" value="${user.id}">${user.name}</option>
                        </#if>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-secondary" id="add" type="button" onclick="clicker()">Add</button>
                </div>
            </div>
        </div>

        <div id="users-list" style="position: inherit; height: 300px; width: 30%; display: inline-block; float: right;
        border: 2px #d4d4d4 solid; border-radius: 6px 0 0 6px; overflow-y: scroll; margin-right: 10%; margin-top: 30px">

        </div>

        <div style="display: block; width: 100%; text-align: center; margin-top: 80px">
            <input type="button" id="send" class="btn btn-secondary" disabled onclick="sendDto()" value="Create">
        </div>
    </form>
</div>
</body>
</html>