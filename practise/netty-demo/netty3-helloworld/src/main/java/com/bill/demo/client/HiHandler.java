package com.bill.demo.client;

import org.jboss.netty.channel.*;

public class HiHandler extends SimpleChannelHandler {

    /**
     * 接收消息
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        //网络传输的数据是字节流，存储在ChannelBuffer中
       /* ChannelBuffer message  = (ChannelBuffer)e.getMessage();
        String s = new String(message.array());*/

        /**
         * 以上还需要解包，比较麻烦，其实netty已经帮助我们考虑了这个问题
         * 在main函数中添加 pipeline.addLast("decoder", new StringDecoder());
         * 就可以用下面的写法了
         */
        String s = e.getMessage().toString();
        System.out.println(s);

        // 回写数据
        /*ChannelBuffer channelBuffers =  ChannelBuffers.copiedBuffer(returnMsg.getBytes());
        ctx.getChannel().write(channelBuffers);*/
        /**
         * 上面这种写法比较麻烦，netty也帮助我们考虑好了，在main函数
         * 添加pipeline.addLast("encoder", new StringEncoder());
         */
        super.messageReceived(ctx, e);
    }

    /**
     * 捕获异常
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    /**
     * 新连接
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");

        super.channelConnected(ctx, e);
    }

    /**
     * 必须是连接已经建立，关闭通道的时候才会触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");

        super.channelDisconnected(ctx, e);
    }


    /**
     * channel关闭的时候触发
      * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");

        super.channelClosed(ctx, e);
    }
}
