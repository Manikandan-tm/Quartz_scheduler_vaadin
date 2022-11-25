package com.example.application.scheduler;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SchedulerService {

    private final Scheduler scheduler;
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public <T extends Job> void schedule(final Class<T> jobClass) {
        final JobDetail jobDetail = SchedulerUtils.jobDetail(jobClass);
        final Trigger trigger = SchedulerUtils.trigger(jobDetail);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            //scheduler.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
