package com.liujj.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Author: liujinjian
 * @Date: 2020/6/12 12:11
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            while (true) {
                System.out.println(Thread.interrupted());
            }

        });
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
        TimeUnit.SECONDS.sleep(3);
    }
}
