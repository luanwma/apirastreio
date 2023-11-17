package utfpr.trabalho.api.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import utfpr.trabalho.api.model.utils.TokenApi;
import utfpr.trabalho.api.model.utils.TokenApiRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.*;

@Component
public class QuartzService implements Job {

    private final TokenApiRepository tokenApiRepository;

    public QuartzService(TokenApiRepository tokenApiRepository) {
        this.tokenApiRepository = tokenApiRepository;
    }


    public void execute(JobExecutionContext context) {

        LocalDateTime localDataTime = LocalDateTime.now();
        out.println("Execute Quartz Automation - 1 minute!!");

        // disparar consultas de objetos


        // adicionar condicional pra refazer o job do token



        if(localDataTime.isAfter(tokenApiRepository.findById(1).get().getExpiredAt())) {

            out.println(localDataTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            TokenApi tokenApi = tokenApiRepository.findById(1).get();
            tokenApi.setBearerToken();
            tokenApi.setExpiredAt();

            tokenApiRepository.save(tokenApi);
        }
    }

}
