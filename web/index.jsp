<%--
${DESCRIPTION}
@author changshengee
@date 2019/05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用WebSocket实时获知比特币价格</title></head>
<body>
<div style="width:400px;margin:20px auto;border:1px solid lightgray;padding:20px;text-align:center;">
    当前比特币价格：￥<span style="color:#FF7519" id="price">10000</span>
    <div style="font-size:0.9em;margin-top:20px">查看的人数越多，价格越高, 当前总共 <span id="total">1</span> 个人在线</div>
    <div style="color:silver;font-size:0.8em;margin-top:20px">以上价格纯属虚构，如有雷同，so what？</div>

</div>
</body>

<script type="text/javascript">
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost:8080/ws/bitCoinServer');
        websocket.onopen = function () {
            websocket.send("客户端连接成功");
        };

        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
        };
        websocket.onerror = function () {
            alert("WebSocket链接发生错误")
        };
        websocket.onclose = function () {
            alert("WebSocket链接关闭")
        };
        window.onbeforeunload = function (ev) {
            closeWebSocket();
        };
    } else {
        alert("当前浏览器 Not Support webSocket");
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        var bitcoin = eval("(" + innerHTML + ")");
        document.getElementById('price').innerHTML = bitcoin.price;
        document.getElementById('total').innerHTML = bitcoin.total;
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
</script>
</html>
