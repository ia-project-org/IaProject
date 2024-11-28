package org.eligibilityms.controller;

import lombok.RequiredArgsConstructor;
import org.eligibilityms.dto.ClientDto;
import org.eligibilityms.model.EligibilityStatus;
import org.eligibilityms.proxy.ClientsMsProxy;
import org.eligibilityms.service.EligibilityStatusService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eligibility")
public class EligibilityController {

    private final ClientsMsProxy clientsMsProxy;

    private final EligibilityStatusService eligibilityStatusService;

    private final RestTemplate restTemplate;

    private static final String DJANGO_BASE_URL = "http://127.0.0.1:8000/api/predict/";

    @GetMapping
    public List<ClientDto> getClientsList(){
        return clientsMsProxy.getClientsList();
    }

    @GetMapping("ok")
    public ResponseEntity<?> getClientsList1(){
        return clientsMsProxy.getClientsList1();
    }
    @PostMapping("/{clientId}")
    public ResponseEntity<?> evaluateClientEligibility(
            @PathVariable("clientId") Long clientId
    ){
        // Appel POST à l'endpoint Django
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(clientsMsProxy.getClientDetails(clientId), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    DJANGO_BASE_URL,
                    request,
                    String.class
            );
            return response;
        } catch (RestClientException e) {
            // Handle potential connection or request errors
            throw new RuntimeException("Error sending POST request", e);
        }
       //return ResponseEntity.ok().body(restTemplate.postForEntity(DJANGO_BASE_URL,clientsMsProxy.getClientDetails(clientId),String.class));
        //return ResponseEntity.ok().body(clientsMsProxy.getClientDetails(clientId));
    }
}
