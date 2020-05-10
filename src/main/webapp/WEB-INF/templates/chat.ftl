<#ftl encoding='UTF-8'>
<#import "/static/css/chatStyles.ftl" as style>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/css/menu.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</head>

<@style.style/>

<script>
    let webSocket;
    function connect() {
        webSocket = new SockJS("http://localhost:8080/chatting");
        document.cookie = 'X-Authorization=' + '12345' + ';path=/';

        let owner = $('#owner').val();
        let box = document.getElementById('box');

        function createMessage(message, owner) {
            var o = message['owner'];
            if (o === owner) {
                box.innerHTML += '<div class="bubbleWrapper">' +
                    '<div class="inlineContainer own">' +
                    '<div class="ownBubble own">' +
                    '<strong>' + owner + '</strong>' +
                    '<br>' +
                    message['content'] +
                    '</div>' +
                    '</div>' +
                    '<span class="own">' + message['date'] + '</span>' +
                    '</div>';
            } else {
                box.innerHTML += '<div class="bubbleWrapper">' +
                    '<div class="inlineContainer">' +
                    '<div class="otherBubble other">' +
                    '<strong>' + message['owner'] + '</strong>' +
                    '<br>' +
                    message['content'] +
                    '</div>' +
                    '</div>' +
                    '<span class="other">' + message['date'] + '</span>' +
                    '</div>';
            }
            scroll();
        }

        webSocket.onmessage = function receiveMessage(response) {
            let data = response['data'];
            let json = JSON.parse(data);
            createMessage(json, owner);
        };

        webSocket.onerror = function (response) {
            alert('Error');
            window.location.href = '/chats';
        }
    }

    function sendMessage(content, owner, chatId) {
        let message = {
            "content": content,
            "owner": owner,
            "chatId": chatId
        };

        webSocket.send(JSON.stringify(message));
        clean();
    }
</script>

<body onload="connect(); scroll()">
<input type="hidden" id="owner" value="${currentUser}">
<div class="wrapper">
    <ul class="navigation">
        <li><a href="/user" title="Profile">Profile</a></li>
        <li><a href="/users" title="All users">All users</a></li>
        <li><a href="/chats" title="Chats">Chats</a></li>
        <li class="logout"><a href="/logout" title="Logout">Logout</a></li>
        <div class="clear"></div>
    </ul>
</div>

<#--<h2 align="center" style="margin-top: 40px">${chat.title}</h2>-->

<div class="chat">
    <div class="chat-box" id="box">
        <#if messages?has_content>
            <#list messages as message>
                <#if message.owner != currentUser>
                    <div class="bubbleWrapper">
                        <div class="inlineContainer">
                            <div class="otherBubble other">
                                <strong>${message.owner}</strong>
                                <br>
                                ${message.content}
                            </div>
                        </div>
                        <span class="other">${message.date}</span>
                    </div>
                <#else >
                    <div class="bubbleWrapper">
                        <div class="inlineContainer own">
                            <div class="ownBubble own">
                                <strong>${message.owner}</strong>
                                <br>
                                ${message.content}
                            </div>
                        </div>
                        <span class="own">${message.date}</span>
                    </div>
                </#if>
            </#list>
        </#if>
    </div>

    <div class="msg-input">
        <div style="margin: 20px">
            <div class="inp-box">
                <input type="text" class="inp" id="message">
            </div>
            <div class="send-box">
                <input type="button" class="send"
                       onclick="sendMessage($('#message').val(), $('#owner').val(), ${chat.id})">
            </div>
        </div>
    </div>

    <div class="chat-users">
        <div class="hdr">
            Users in this chat
        </div>

        <div class="users-list">
            <div class="list-group">
                <form action="/profile" method="get">
                    <input id="own" type="submit" class="list-group-item" value="♔ ${chat.owner.username}">
                    <input type="hidden" value="${chat.owner.username}" id="username">
                </form>
                <#list chat.users as user>
                    <form action="/user" method="get">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" class="list-group-item" value="${user.username}">
                    </form>
                </#list>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    function scroll() {
        var block = document.getElementById("box");
        block.scrollTop = block.scrollHeight;
    }

    function clean() {
        document.getElementById("message").value = '';
    }
</script>

</html>