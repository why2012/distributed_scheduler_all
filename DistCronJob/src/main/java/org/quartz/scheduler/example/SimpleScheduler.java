package org.quartz.scheduler.example;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.scheduler.example.jobs.PrintWordsJob;
import static org.quartz.DateBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

import java.util.concurrent.TimeUnit;

public class SimpleScheduler {

    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).withIdentity("myJob","group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").
                startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
    }
}
