package com.liujj.guava;


import java.util.Arrays;

/**
 * @Author: liujinjian
 * @Date: 2020/6/8 14:11
 */
public class GuavaTest {

    public static void main(String[] args) {

        test("1", "2");


    }

    public static void test(String... t) {
        System.out.println(Arrays.toString(t));
    }
}
