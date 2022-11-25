package com.example.application.scheduler;

import org.quartz.*;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class SchedulerUtils {
    public SchedulerUtils(){

    }
    public static JobDetail jobDetail(final Class<? extends Job> jobClass) {
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("job1",jobClass.getSimpleName());
        return JobBuilder.newJob().ofType(jobClass)
                .storeDurably()
                .withIdentity(jobClass.getCanonicalName())
                .withDescription("Invoke Sample Job service...")
                .setJobData(jobDataMap)
                .build();
    }


    public static Trigger trigger(final JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withDescription("Sample trigger")
                //.withSchedule((cronSchedule("0 * 21 * * ?")))
                .withSchedule(simpleSchedule().withIntervalInMilliseconds(2000).repeatForever())
                .startAt(new Date(System.currentTimeMillis()))
                .build();
    }

}