package com.ticking.clock.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Meridian implements Runnable {

    private String meridian;

    @Autowired
    private Hour hour;
    public Meridian(Hour hour) {
        this.hour = hour;
    }

    public String getMeridian() {
        return meridian;
    }

    public void setMeridian(String meridian) {
        this.meridian = meridian;
    }

    @Override
    public void run() {
        while (true) {
            hour.getHourLock().readLock().lock();
            if (hour.getHour() >= 11) {
                if (meridian.equals("AM")) {
                    meridian = "PM";
                } else {
                    meridian = "AM";
                }
            }
            hour.getHourLock().readLock().unlock();
        }
    }
}
