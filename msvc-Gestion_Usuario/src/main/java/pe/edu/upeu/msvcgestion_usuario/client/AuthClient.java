package pe.edu.upeu.msvcgestion_usuario.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthClient {

    private final RestTemplate restTemplate;

    public AuthClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    public String encryptPassword(String rawPassword) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("password", rawPassword);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8081/auth/encrypt-password", request, String.class
        );

        return response.getBody();
    }
}
