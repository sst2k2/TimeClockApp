package com.ticking.clock.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class Second implements Runnable {
    private int second;


    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    private ReentrantReadWriteLock lock;

    @PostConstruct
    public void init(){
        lock = new ReentrantReadWriteLock();
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                    lock.writeLock().lock();
                    if (second < 59) {
                        second++;
                    } else {
                        second = 0;
                    }
                    lock.writeLock().unlock();
                    Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
