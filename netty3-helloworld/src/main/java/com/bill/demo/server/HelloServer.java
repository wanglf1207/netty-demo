package com.bill.demo.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloServer {
    public static void main(String[] args) {

        // 1. 创建服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        // 2. 创建两个线程池
        /**
         *  boss和worker中的一个线程分配一个selector，boss的selector主要是用来负责监听端口的，
         *  worker的selector是用来负责读写任务的
         */

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        // 3. 给服务类设置一个工厂NioServerSocketChannelFactory
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));

        // 设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("helloHandler",new HelloHandler());
                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(10101));

    }
}
