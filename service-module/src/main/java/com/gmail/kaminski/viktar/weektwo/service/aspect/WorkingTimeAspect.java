package com.gmail.kaminski.viktar.weektwo.service.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class WorkingTimeAspect {

    private static final Logger logger = LogManager.getLogger(WorkingTimeAspect.class);
    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:SSSS");

    private long startTime;

    @Pointcut("execution(* com.gmail.kaminski.viktar.weektwo.service.DocumentService.*(..))")
    public void allServiceMethod() {
    }

    @Before("allServiceMethod()")
    public void beforeServiceMethod(JoinPoint jp) {
        Date date = new Date();
        startTime = date.getTime();

    }

    @After("allServiceMethod()")
    public void afterServiceMethod(JoinPoint jp) {
        Date date = new Date();
        long stopTime = date.getTime();
        String dateString = format.format(date);
        String dateString1 = format.format(startTime);
        logger.info(jp.getSignature().getName() + " > " + "start: " + dateString1 +
                " stop: " + dateString + " duration: " + (stopTime - startTime));
    }

}
