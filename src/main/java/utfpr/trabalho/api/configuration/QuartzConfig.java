package utfpr.trabalho.api.configuration;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utfpr.trabalho.api.service.QuartzService;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(QuartzService.class)
                .withIdentity("trackingJobAutomation")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myJobTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("trackingJobAutomation")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();
    }
}