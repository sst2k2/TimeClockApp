package com.ticking.clock.Beans;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
/*@DependsOn({"second","minute","hour", "meridian"})*/
public class Clock {


    @Autowired
    private Second second;

    @Autowired
    private Minute minute;

    @Autowired
    private Hour hour;

    @Autowired
    private Meridian meridian;

   @Autowired
    private ThreadPoolTaskExecutor executor;

   @Autowired
   private Offset offset;

    public Clock(Second second, Minute minute, Hour hour, Meridian meridian) {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.meridian = meridian;
    }

    @PostConstruct
    public void init() {
        System.out.println("Clock Created");
        executor.execute(second);
        executor.execute(minute);
        executor.execute(hour);
        executor.execute(meridian);

    }

    public Second getSecond() {
        return second;
    }

    public Minute getMinute() {
        return minute;
    }

    public Hour getHour() {
        return hour;
    }

    public Meridian getMeridian() {
        return meridian;
    }
    public Offset getOffset(){
        return offset;
    }
}
