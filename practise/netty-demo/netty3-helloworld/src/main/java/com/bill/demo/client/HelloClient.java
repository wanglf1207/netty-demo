package com.bill.demo.client;

import com.bill.demo.server.HelloHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloClient {
    public static void main(String[] args) {

        ClientBootstrap bootstrap = new ClientBootstrap();

        //线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        // socket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));

        //设置管道
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("hiHandler",new HiHandler());
                return pipeline;
            }
        });
        // 连接服务器
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1",10101));
        Channel channel = connect.getChannel();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("请输入");
            channel.write(scanner.next());
        }
    }
}
