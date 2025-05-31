package com.github.axinger;

import lombok.Locked;
import org.junit.Test;

/*
@Locked

用于实现ReentrantLock（可重入锁）。
ReentrantLock也是Java当中提供的一种锁，这种锁和synchronized类似也可以起到互斥使用，
它允许同一个线程在持有锁的情况下再次获取该锁（可重入性），保证线程安全。
（ReentrantLock用起来非常简单，不了解的可以查一下ReentrantLock，，不仅方便的提供加锁、解锁方法，还提供了公平跟非公平的两种实现
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockedExample {
    private int value = 0;
    public final Lock baseLock = new ReentrantLock();

    @Locked.Read
    public int getValue() {
        return value;
    }

    @Locked.Write
    public void setValue(int newValue) {
        value = newValue;
    }

    @Locked("baseLock")
    public void foo() {
        System.out.println("bar");
    }
}
