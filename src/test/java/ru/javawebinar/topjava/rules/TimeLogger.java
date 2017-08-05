package ru.javawebinar.topjava.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeLogger implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(TimeLogger.class);
    private static long resultTime = 0;
    private static long timeConsumedMillis;
    @Override
    public Statement apply(Statement base, Description description) {
        long start = System.currentTimeMillis();
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                log.info("start test");
                base.evaluate();
                long finish = System.currentTimeMillis();
                timeConsumedMillis = finish - start;
                log.info("end test in  "+timeConsumedMillis + " sec");
                resultTime+=timeConsumedMillis;
            }
    };


    }

    public  static  long getResultTime() {
        return resultTime;
    }
}

