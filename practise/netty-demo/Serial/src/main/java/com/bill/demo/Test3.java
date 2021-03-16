package com.bill.demo;

import io.netty.channel.ChannelOutboundBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {

        /**
         * 序列化
         */
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeInt(101);
        buffer.writeDouble(80.1);

        byte[] bytes = new byte[buffer.writerIndex()];
        buffer.readBytes(bytes);

        System.out.println(Arrays.toString(bytes));

        //====================================

        /**
         * 反序列化
         */
       ChannelBuffer channelBuffer = ChannelBuffers.wrappedBuffer(bytes);
       System.out.println(channelBuffer.readInt());
       System.out.println(channelBuffer.readDouble());;
    }
}
