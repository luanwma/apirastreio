package utfpr.trabalho.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
@Service
@AllArgsConstructor
public class TrackingService implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String user = "50485854000183";

    private final String password = "vXEKCs1MNq1FM1SweyhkDiDJTEHGXlS1evxBJUpR";

    private final String urlBase = "https://apphom.correios.com.br/rastro";





    public String trackingOneObject(String codObject) {
        RestTemplate restTemplate = new RestTemplate();
        String url = urlBase + "/v1/objetos/" + codObject; //QQ177840769BR

        return restTemplate.getForObject(url, String.class);
    }

    public String gerarToken(String user, String password) {
        String url = urlBase + "/v1/autentica/";
        RestTemplate restTemplate = new RestTemplate();

        // Codifica as credenciais para Basic Auth
        String auth = this.user + ":" + this.password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        // Configura a requisição
        String requestJson = "{" + "\"Authorization\": \"Basic " + encodedAuth + "\"," + "}";

        return restTemplate.postForObject(url, requestJson, String.class);
    }

}