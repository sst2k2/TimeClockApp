package com.ticking.clock.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class Hour implements Runnable {

    private int hour;

    @Autowired
    private Minute minute;

    ReentrantReadWriteLock hourLock;

    public Hour(Minute minute) {
        this.minute = minute;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public ReentrantReadWriteLock getHourLock() {
        return hourLock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                minute.getMinuteLock().readLock().lock();
                if (minute.getMinute() >= 59) {
                    hourLock.writeLock().lock();
                    if (hour < 11) {
                        hour++;
                    } else {
                        hour = 0;

                    }
                    hourLock.writeLock().unlock();
                }
                minute.getMinuteLock().readLock().unlock();
                Thread.sleep(14300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
    @Bean
    public void init(){
        hourLock = new ReentrantReadWriteLock();
    }

}
