<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

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

<div class="list-group" style="width: 36%; margin-left: 15%; margin-bottom: 2%">
    <#list users as user>
        <div class="list-group-item list-group-item-action flex-column align-items-start" style="margin-top: 20px">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">${user.name}</h5>
                <small class="text-muted">Chats: ${user.chats}</small>
            </div>
            <form action="/user" method="get" style="display: inline-block; float: right; margin-top: 10px !important">
                <input type="hidden" name="id" value="${user.id}">
                <button type="submit" class="btn btn-light" style="border: #4e555b 1px solid">Profile</button>
            </form>
        </div>
    </#list>
</div>

</body>
</html>