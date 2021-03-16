package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    protected void messageReceived(ChannelHandlerContext ctx, String  msg) throws Exception {
        System.out.printf("messageReceived : " + msg);

        //会写
        ctx.channel().writeAndFlush("服务器返回信息");
        ctx.writeAndFlush("也可以这样返回");
    }
}
