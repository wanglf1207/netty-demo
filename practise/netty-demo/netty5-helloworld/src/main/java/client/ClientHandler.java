package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler {
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到的消息： " + msg);
    }
}
