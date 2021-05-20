<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hey!</title>
    <script type="text/javascript">

        var websocket = null;

        window.onload = function() { // URI = ws://10.16.0.165:8080/WebSocket/ws
            connect('ws://' + window.location.host + '/hey/ws');
            document.getElementById("chat").focus();


            /*event.preventDefault();
            connect('ws://' + window.location.host  + '/WebSocketRooms/ws/' +
                document.getElementById('chatroom').value);*/
        }

        function connect(host) { // connect to the host websocket
            if ('WebSocket' in window)
                websocket = new WebSocket(host);
            else if ('MozWebSocket' in window)
                websocket = new MozWebSocket(host);
            else {
                writeToHistory('Get a real browser which supports WebSocket.');
                return;
            }

            websocket.onopen    = onOpen; // set the 4 event listeners below
            websocket.onclose   = onClose;
            websocket.onmessage = onMessage;
            websocket.onerror   = onError;
        }

        function logOut(){
            websocket.send("Logged Out");
        }

        function outElection(){
            websocket.send("Saiu da eleicao");
        }

        function onOpen(event) {
            writeToHistory('Connected to ' + window.location.host + '.');
            document.getElementById('chat').onkeydown = function(key) {
                if (key.keyCode == 13)
                    doSend(); // call doSend() on enter key press
            };
        }

        function onClose(event) {
            writeToHistory('WebSocket closed (code ' + event.code + ').');
            document.getElementById('chat').onkeydown = null;
        }

        function onMessage(message) { // print the received message
            writeToHistory(message.data);
        }

        function onError(event) {
            writeToHistory('WebSocket error.');
            document.getElementById('chat').onkeydown = null;
        }

        function doSend() {
            var message = document.getElementById('chat').value;
            if (message != '')
                websocket.send(message); // send the message to the server
            document.getElementById('chat').value = '';
        }

        function writeToHistory(text) {
            var history = document.getElementById('history');
            var line = document.createElement('p');
            line.style.wordWrap = 'break-word';
            line.innerHTML = text;
            history.appendChild(line);
            history.scrollTop = history.scrollHeight;
        }

    </script>
</head>
<body>
<s:form action="listToVoteAction" method="post">
<div style="margin: 0 auto; text-align: center">
    <p style="font-size:30px; font-family: 'Courier New'">
    <s:text name="Listas: " />
    </p>
    <p style="font-size:30px; font-family: 'Courier New'">
        <c:forEach items="${heyBean.listVote}" var="value">
            <input type="radio" id="${value}" name="listSelectedToVote" value="${value}">
            <label for="${value}">${value}</label><br>
        </c:forEach>
    </p>
    <br>
    <button type="submit" onclick = "outElection()" >Submit</button>
</div>
</s:form>
</body>
</html>