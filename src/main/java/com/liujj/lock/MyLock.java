package com.liujj.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: liujinjian
 * @Date: 2020/6/12 9:43
 */
public class MyLock {


    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unLock() {
        sync.release(1);
    }


    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (compareAndSetState(1, 0)) {
                return true;
            }
            return false;
        }

    }


}
