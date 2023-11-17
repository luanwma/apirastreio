package utfpr.trabalho.api.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.*;

@Component
public class QuartzService implements Job {



    public void execute(JobExecutionContext context) {

        LocalDateTime localDataTime = LocalDateTime.now();
        out.println("Execute Quartz Automation - 1 minute!!");

        // disparar consultas de objetos

        // if(localDataTime.isAfter(LocalDateTime token.getTimeExpiration()))
        // adicionar condicional pra refazer o job do token
            out.println(localDataTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

    }

}
