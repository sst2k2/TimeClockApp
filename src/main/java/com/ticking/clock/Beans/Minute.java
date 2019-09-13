package com.ticking.clock.Beans;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class Minute implements Runnable {
    private int minute;


    @Autowired
    private Second second;

    public Minute(Second second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }
    private ReentrantReadWriteLock minuteLock;

    @PostConstruct
    private void init(){
        this.minuteLock = new ReentrantReadWriteLock();
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public ReentrantReadWriteLock getMinuteLock() {
        return minuteLock;
    }

    @Override
    public void run() {
        while (true) {
            try{
                    minuteLock.writeLock().lock();
                    if (second.getSecond() >= 59) {
                        second.getLock().readLock().lock();
                            if (minute < 59) {
                                minute++;
                            } else {
                                minute = 0;
                            }
                        second.getLock().readLock().unlock();
                        }
                    minuteLock.writeLock().unlock();
                    Thread.sleep(900);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            }
        }
    }

