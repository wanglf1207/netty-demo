netty自定义协议
自定义序列化协议
自定义数据包（报文）协议

# netty快速入门教程



netty版本大致分为netty3.x和netty4.x,netty5.x

netty可以运用在哪些领域?

1、分布式进程通讯

例如hadoop，dubbo，akka等具有分布式功能的框架，底层RPC通信都是基于netty实现的，这些框架使用的版本通常都还在3.x

2、游戏服务器开发

最新的游戏服务器有部分公司已经开始采用netty4.x或netty5.x



hello world总结

SimpleChannelHandler 消息接收和写的事件，除了这些还有很多其他的方法，例如exceptionCaught、channelConnected、channelDisconnected等等。



例如我们想把某个IP列入黑名单，可以找到该通道，然后channelClosed掉



