package com.example.demo.infrastructure.configquartz.detail;

import com.example.demo.infrastructure.configquartz.job.BootJob;
import org.quartz.JobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobDetail {

    @Bean(name = "bootJob")
    public JobDetail bootJob() {
        return JobBuilder.newJob().ofType(BootJob.class)
                .withIdentity("RUN_BOOT", "BOOT_JOB")
                .withDescription("Invoke Sample Job service...")
                .storeDurably(true)
                .build();
    }

}
