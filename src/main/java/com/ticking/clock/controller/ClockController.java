package com.ticking.clock.controller;

import com.ticking.clock.Beans.Clock;
import com.ticking.clock.Beans.Meridian;
import com.ticking.clock.Beans.Offset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@Controller
@RequestMapping("/clock")
public class ClockController{
    private final Logger logger = LoggerFactory.getLogger(ClockController.class);



    @Autowired
    private Clock clock;

    @Autowired
    private Meridian meridian;

    @Autowired
    private Offset offset;
    private Calendar calender;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView clock(ModelAndView model) {
        logger.info("Hi World!");
        model.addObject("clock",clock);
        model.addObject("offset",offset);
        model.setViewName("clockview");
        return model;
    }

    @RequestMapping(value = "goBackInTime",method = RequestMethod.POST)
    public ModelAndView goBackInTime(@ModelAttribute("offset") Offset offset, BindingResult result, ModelAndView model){
        clock.getOffset().setOffset(offset.getOffset());
        calender.setTimeInMillis(calender.getTimeInMillis()-(offset.getOffset()*60*60*1000));
        clock.getSecond().setSecond(calender.get(Calendar.SECOND));
        clock.getMinute().setMinute(calender.get(Calendar.MINUTE));
        clock.getHour().setHour(calender.get(Calendar.HOUR));
        clock.getMeridian().setMeridian((calender.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
        model.addObject("clock",clock);
        model.setViewName("clockview");
        return model;
    }
    @PostConstruct
    public void init() {
        calender = Calendar.getInstance();
        clock.getSecond().setSecond(calender.get(Calendar.SECOND));
        clock.getMinute().setMinute(calender.get(Calendar.MINUTE));
        clock.getHour().setHour(calender.get(Calendar.HOUR));
        clock.getMeridian().setMeridian((calender.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
        clock.getOffset().setOffset(0);
        System.out.println(calender.getTimeZone().getDisplayName());
    }
}
