package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Server {
    public static void main(String[] args) {


            //服务类
            ServerBootstrap bootstrap = new ServerBootstrap();

            // boss和worker,netty是线程池，5不是线程池，做了时间循环组，可以理解成线程池
            EventLoopGroup boss = new NioEventLoopGroup();
            EventLoopGroup worker = new NioEventLoopGroup();

        try {
            // 设置线程池
            bootstrap.group(boss,worker);
            // 设置socket工厂
            bootstrap.channel(NioServerSocketChannel.class);

            // 设置管道工厂
            bootstrap.childHandler(new ChannelInitializer<Channel>() {

                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ServerHandler());

                }
            });

            // 设置参数,TCP参数
            // serverSocketChannel设置，链接缓冲池的大小
            bootstrap.option(ChannelOption.SO_BACKLOG,2048);
            // socketChannel的设置，维持连接的活跃，清除死连接
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
            // socketChannel的设置，关闭延迟发送
            bootstrap.childOption(ChannelOption.TCP_NODELAY,true);
            // 绑定端口
            ChannelFuture future = bootstrap.bind(10101);

            System.out.printf("服务端启动");
            // 等待服务端关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
