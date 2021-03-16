package com.bill.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) throws IOException {
        int id =101;
        int age = 21;

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        arrayOutputStream.write(int2bytes(id));
        arrayOutputStream.write(int2bytes(age));

        byte[] byteArray = arrayOutputStream.toByteArray();
        // 这就是序列化的结果
        System.out.println(Arrays.toString(byteArray));


        //==================================

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        byte[] idBytes = new byte[4];
        // 读四个
        int b = arrayInputStream.read(idBytes);
        System.out.println("id:" + bytes2int(idBytes) );


        byte[] ageBytes = new byte[4];
        // 读四个
        arrayInputStream.read(ageBytes);
        System.out.println("age:" + bytes2int(ageBytes) );

       /* System.out.println(Integer.toBinaryString(id));

        byte [] a = int2bytes(id);
        System.out.println(bytes2int(a));*/
    }

    /**
     * 两种转换方式：
     * 1. 大端字节序列（先写高位，再写低位）
     * 2. 小端序列，先写低位，再写高位
     * @param i
     * @return
     */
    public static byte[] int2bytes(int i ) {
        // int在内存中占4个字节
        byte[] bytes = new byte[4];

        /**
         * 先取高位，向右移三个字节长度
         * 00000000 00000000 00000000 01100101
         * 百度：大小端字节序列
         */
        bytes[0] = (byte)(i >> 3*8);
        bytes[1] = (byte)(i >> 2*8);
        bytes[2] = (byte)(i >> 1*8);
        bytes[3] = (byte)(i >> 0*8);

        return  bytes;

    }

    /**
     * 将二进制数组转化为整形
     * @param bytes
     * @return
     */
    public static int bytes2int(byte[] bytes) {
        return (bytes[0] << 3*8) |
                (bytes[1] << 2*8) |
                (bytes[2] << 1*8) |
                (bytes[3] << 0*8);

    }
}
