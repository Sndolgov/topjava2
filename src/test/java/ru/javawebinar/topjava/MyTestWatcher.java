package ru.javawebinar.topjava;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Сергей on 19.09.2018.
 */
public class MyTestWatcher extends TestWatcher {
    private long startTime;
    private long endTime;
    private Map<String, Long> timeAndNameOfMethods;
    private static final Logger log = LoggerFactory.getLogger(MyTestWatcher.class);


    public MyTestWatcher(Map<String, Long> timeAndNameOfMethods) {
        this.timeAndNameOfMethods = timeAndNameOfMethods;
    }

    @Override
    protected void starting(Description description) {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void finished(Description description) {
        endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        String name = description.getMethodName();
        log.info("method: {} -> time {}", name, time);
        timeAndNameOfMethods.put(name, time);
    }

    public static void printMethodNameAndTime(Map<String, Long> timeAndNameOfMethods){
        timeAndNameOfMethods.forEach((k,v)-> System.out.printf("method: %s -> time %d\n", k,v));

    }
}
