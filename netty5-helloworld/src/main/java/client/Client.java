package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import server.ServerHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        // 创建服务类
        Bootstrap bootstrap = new Bootstrap();

        // 创建worer，没用boss了
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            //设置线程池
            bootstrap.group(worker);

            //设置socket工厂
            bootstrap.channel(NioSocketChannel.class);

            // 设置管道
            bootstrap.handler(new ChannelInitializer<Channel>() {
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ClientHandler());
                }
            });

            // 连接
                 ChannelFuture connect =bootstrap.connect("127.0.0.1",10101);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.printf("请输入:");
                String line = bufferedReader.readLine();
                connect.channel().writeAndFlush(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
        }

    }
}
