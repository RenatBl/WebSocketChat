<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${user.name}</title>
    <link rel="stylesheet" type="text/css" href="/static/css/menu.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
</head>

<style>
    .card {
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        border-radius: 0 0 5px 5px;
        max-width: 300px;
        margin: auto;
        text-align: center;
        font-family: arial;
    }

    .title {
        color: grey;
        font-size: 18px;
    }

    .posts {
        border: none;
        outline: 0;
        display: inline-block;
        padding: 8px;
        color: white;
        background-color: #000;
        text-align: center;
        cursor: pointer;
        width: 100%;
        font-size: 18px;
        border-radius: 0 0 5px 5px;
    }

    .posts:hover {
        opacity: 0.7;
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

<div class="card">

    <img src="https://image.flaticon.com/icons/png/512/56/56745.png" alt="" style="width:100%; margin-top: 10px">
    <h1>${user.name}</h1>
</div>

</body>
</html>