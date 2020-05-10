<#macro style>
    <style>
        html, body {
            margin: 0;
            padding: 0;
        }
        .chat {
            font-family: 'Avenir', serif;
            width: 900px;
            margin-left: 10%;
            margin-top: 2%;
            margin-bottom: 1%;
            border: 6px rgba(212,228,239,1) solid;
            border-radius: 6px 0 0 6px;
        }
        .chat-box {
            overflow-y: scroll;
            background: #D3D3D3;
            height: 600px;
        }
        .no-msg {
            margin-top: 40px;
            margin-right: auto;
            margin-left: auto;
            text-align: center;
            font-family: 'Avenir',sans-serif;
            font-size: 16pt;
        }
        .bubbleWrapper {
            padding: 10px 10px;
            display: flex;
            justify-content: flex-end;
            flex-direction: column;
            align-self: flex-end;
            color: #fff;
        }
        .inlineContainer {
            display: inline-flex;
        }
        .inlineContainer.own {
            flex-direction: row-reverse;
        }
        .ownBubble {
            min-width: 60px;
            max-width: 700px;
            padding: 14px 18px;
            margin: 6px 8px;
            background-color: #5b5377;
            border-radius: 16px 16px 0 16px;
            border: 1px solid #443f56;

        }
        .otherBubble {
            min-width: 60px;
            max-width: 700px;
            padding: 14px 18px;
            margin: 6px 8px;
            background-color: #6C8EA4;
            border-radius: 16px 16px 16px 0;
            border: 1px solid #54788e;

        }
        .own {
            align-self: flex-end;
        }
        .other {
            align-self: flex-start;
        }
        span.own,
        span.other{
            font-size: 14px;
            color: grey;
        }
        .msg-input {
            border-top: 5px rgba(212,228,239,1) solid;
            min-height: 70px;
            vertical-align: center;
            display: flex;
        }

        .inp-box {
            display: inline-block;
            float: left
        }

        .inp {
            padding-left: 8px;
            border-radius: 8px 0 0 8px;
            border:1px solid #ccc;
            width: 810px;
            height: 32px;
            position: inherit;
            outline: none;
            -webkit-appearance: none;
            vertical-align: middle;
        }
        .send-box {
            display: inline-block;
            float: left;
            border: 1px #ccc solid;
            width: 30px;
            height: 32px;
            border-radius: 0 8px 8px 0;
            background-color: #e0e0e0;
        }
        .send {
            width: 30px;
            height: 30px;
            border: none;
            background: url("https://img.icons8.com/ios-glyphs/30/000000/telegram-app.png");
            outline: none;
            -webkit-appearance: none;
        }
        .send:hover {
            background: url("https://img.icons8.com/material-two-tone/30/000000/telegram-app.png");
        }
        .send:active {
            transform: translateY(4px);
        }
        .chat-users{
            position: absolute;
            top: 70px;
            right: 198px;
            height: 690px;
            overflow: hidden;
            border-top: 6px rgba(212,228,239,1) solid;
            border-right: 6px rgba(212,228,239,1) solid;
            border-bottom: 6px rgba(212,228,239,1) solid;
            border-radius: 0 6px 6px 0;
            width: 275px;
        }
        .hdr {
            position: inherit;
            vertical-align: center;
            height: 50px;
            width: 250px;
            margin-top: 10px;
            margin-left: 12px;
            text-align: center;
            font-family: 'Georgia',sans-serif;
            font-size: 18pt;
            line-height: 50px;
        }
        .users-list {
            background: #fcfcfc;
            height: 611px;
            overflow-y: scroll;
            overflow-x: hidden;
            position: inherit;
            width: 264px;
            border-top: 1px #ededed solid;
            margin-top: 70px;
            margin-left: 6px;
        }
        .list-group-item {
            width: 230px;
            margin-left: 8px;
            margin-top: 8px;
            height: 50px;
            border: 1px #ededed solid;
            border-radius: 2px;
            font-family: 'Georgia', sans-serif;
            font-size: 15pt;
            padding-left: 20px;
            text-align: left;
            vertical-align: center;
        }
        .list-group-item:hover {
            background: #6C8EA4;
        }
        .list-group-item:active {
            transform: translateY(2px);
        }
        #own {
            background: #ffeeba;
        }
        #own:hover {
            background: #54788e;
        }
        /*    Navigation*/
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
</#macro>