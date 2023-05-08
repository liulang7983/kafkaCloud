package com.test;

/**
 * @author ming.li
 * @date 2023/2/24 14:17
 */
public class ThreadTest {
    public static void main(String[] args) {
        int s=Integer.SIZE-3;
        int a=-1<<s;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a);
        //System.out.println(Integer.toBinaryString(-1));
        int b=0<<s;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);
        System.out.println(b>a);
        System.out.println(Integer.MAX_VALUE);
    }
}
