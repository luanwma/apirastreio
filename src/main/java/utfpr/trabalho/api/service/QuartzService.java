package utfpr.trabalho.api.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import static java.lang.System.*;

@Component
public class QuartzService implements Job {

    public void execute(JobExecutionContext context) {
        out.println("Execute Quartz Service - 1 minute!!");
    }

}
