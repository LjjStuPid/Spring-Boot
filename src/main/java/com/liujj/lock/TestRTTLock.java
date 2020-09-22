package com.liujj.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: liujinjian
 * @Date: 2020/6/9 12:30
 */
public class TestRTTLock {

    private double x, y;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    private Lock readLock = lock.readLock();

    /**
     * 写锁
     */
    private Lock writeLock = lock.writeLock();

    public double read() {
        readLock.lock();
        try {
            return Math.sqrt(x * x + y * y);
        } finally {
            readLock.unlock();
        }
    }

    public void move(double deltaX, double deltaY) {
        writeLock.lock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            writeLock.unlock();
        }
    }
}
