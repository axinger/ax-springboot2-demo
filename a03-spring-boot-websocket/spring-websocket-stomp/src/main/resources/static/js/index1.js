var stompClient = null;
//加载完浏览器后  调用connect（），打开双通道
$(function () {
    //打开双通道
    connect()

    var response = $("#connect");
    response.append("<p>链接成功</p>");
})
//强制关闭浏览器  调用websocket.close（）,进行正常关闭
window.onunload = function () {
    disconnect()
}

function connect() {
    var socket = new SockJS('http://127.0.0.1:8080/endpointOyzc'); //连接SockJS的endpoint名称为"endpointOyzc"
    stompClient = Stomp.over(socket);//使用STMOP子协议的WebSocket客户端
    stompClient.connect({}, function (frame) {//连接WebSocket服务端
        console.log('Connected:' + frame);
        //通过stompClient.subscribe订阅/topic/getResponse 目标(destination)发送的消息
        stompClient.subscribe('/topic/getResponse', function (response) {
            console.log('/topic/getResponse' + JSON.parse(response.body))
            showResponse(JSON.parse(response.body));
        });
    });
}

//关闭双通道
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function showResponse(message) {
    var response = $("#response");
    response.append("<p>" + message.userName + "</p>");
}
