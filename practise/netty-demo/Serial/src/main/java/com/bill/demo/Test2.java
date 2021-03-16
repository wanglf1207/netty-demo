package com.bill.demo;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 在Test1中我们为int做了序列化和反序列化，难道要为每种数据类型都做相同的操作吗？
 * 有没用简单点的方法，有，利用nio的ByteBuffer
 */
public class Test2 {
    public static void main(String[] args) {

        int id = 101;
        int age = 21;

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(id);
        buffer.putInt(age);

        System.out.println(Arrays.toString(buffer.array()));

        //=============分割线==============================

        byte[] array = buffer.array();
        ByteBuffer buffer1 = ByteBuffer.wrap(array);
        System.out.println("id: " + buffer1.getInt());
        System.out.println("age: " + buffer1.getInt());

        /**
         * 这样序列化有个问题，初始化的bytebuffer不能扩容ByteBuffer.allocate(8);
         */
    }
}
