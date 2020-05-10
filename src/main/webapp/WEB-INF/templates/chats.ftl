<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chats</title>
    <link rel="stylesheet" type="text/css" href="/static/css/menu.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
</head>


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
    <div style="display: inline-block; margin-top: 2%; margin-left: 6%; height: 650px; width: 50%; border: 3px #d4d4d4 solid; border-radius: 8px; padding: 15px">
        <#if chats?has_content>
            <div class="list-group" style="min-height: 612px; width: 90%; margin-left: 45px; overflow-y: scroll;
    border: 1px #d4d4d4 solid; border-radius: 4px 0 0 4px;">
                <#list chats as chat>
                    <#if user.id != chat.owner.id>
                        <div class="list-group-item list-group-item-action flex-column align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">${chat.title}</h5>
                                <small class="text-muted">${chat.users?size} users</small>
                            </div>
                            <form action="/chat" method="get" style="margin-top: 10px; float: right">
                                <input type="hidden" name="id" value="${chat.id}">
                                <button type="submit" class="btn btn-outline-danger">Chat</button>
                            </form>
                        </div>
                    <#else >
                        <div class="list-group-item list-group-item-action flex-column align-items-start"
                             style="background: #FFFFE0">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">♔ ${chat.title}</h5>
                                <small class="text-muted">${chat.users?size} users</small>
                            </div>
                            <div style="margin-top: 10px; float: left">
                                (You owner)
                            </div>
                            <form action="/chat" method="get" style="margin-top: 10px; float: right">
                                <input type="hidden" name="id" value="${chat.id}">
                                <button type="submit" class="btn btn-outline-danger">Chat</button>
                            </form>
                        </div>
                    </#if>
                </#list>
            </div>
        <#else >
            <div class="message" style="width: 100%; padding: 2%; text-align: center; color: #333333">
                <h3>No chats. Create your first chat</h3>
            </div>
        </#if>
    </div>
    <div style="display: inline-block; margin-left: 60px">
        <form action="/createChat" method="get">
            <button type="submit" class="btn btn-success btn-lg">Create new chat</button>
        </form>
    </div>
</div>

</body>
</html>