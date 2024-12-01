package org.bankms.clientsms.controller;

import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.config.ApplicationPropertiesConfiguration;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.service.ClientService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@RefreshScope
public class ClientController {

    private final JobLauncher jobLauncher;

    private final Job importClientsJob;

    private final ClientService clientService;

    private final ApplicationPropertiesConfiguration propertiesConfiguration;


    @GetMapping("/details")
    public ResponseEntity<?> getPaginatedClientsDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
       return ResponseEntity.ok(clientService.getClientsDetails(page,size));
    }

    @GetMapping
    public ResponseEntity<?> getPaginatedClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        jobLauncher.run(importClientsJob,new JobParameters());

        //return ResponseEntity.ok(clientService.getClients(page,size).stream().toList().subList(0, propertiesConfiguration.getLimitDeProduits()));
        return ResponseEntity.ok().body(new ArrayList<>().add(new Client()));
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @GetMapping("/{clientId}")
    public ClientDetails getClientDetails(@PathVariable("clientId") Long clientId){
        return clientService.getClientDetails(clientId);
    }

    @PostMapping("/details")
    public ResponseEntity<?> saveClientDetails(@RequestBody ClientDetails clientDetails){
        return ResponseEntity.ok().body(clientService.saveClientDetails(clientDetails));
    }
}
