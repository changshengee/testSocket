# WebSocket
 
服务端主动向客户端发送数据

## 优点

实时，节约宽带，无浪费

## 注意点

用注解@ServerEndpoint("/ws/bitcoinServer")把它标记为一个WebSocket Server

因为Struts会把所有的请求都拦截下来，所以需要加一个例外
<constant name="struts.action.excludePattern" value="/ws/bitcoinServer" />
以保证webSocket请求能够被正常捕捉住，不然就被Struts搞跑了

如果做了nginx和tomcat整合的话，那么nginx 需要加上这么一段话，才能够正常的把webSocket请求交给tomcat,不然tomcat也不知道怎么处理
location /ws/ {
        proxy_pass http://127.0.0.1:11180;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
}
 
 
