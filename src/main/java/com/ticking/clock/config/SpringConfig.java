package com.ticking.clock.config;

import com.ticking.clock.Beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@EnableWebMvc
@Configuration
@ComponentScan("com.ticking.clock")
public class SpringConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/").setCachePeriod(31556926);
    }

    @Bean
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver
                = new UrlBasedViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Override
    public void configureDefaultServletHandling(

            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
         executor.setCorePoolSize(5);
         executor.setMaxPoolSize(10);
         return executor;
    }

    @Bean
    @Scope("prototype")
    public ReentrantReadWriteLock getRWLock(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        return lock;
    }

    @Bean
    public Second second(){
        return new Second();
    }

    @Bean
    public Minute minute(){
        return new Minute(second());
    }

    @Bean
    public Hour hour(){
        return new Hour(minute());
    }

    @Bean
    public Meridian meridian(){
        return new Meridian(hour());
    }

    @Bean
    public Clock clock(){
        return new Clock(second(),minute(),hour(),meridian());
    }

    @Bean
    public Offset offset(){
        return new Offset();
    }

}
