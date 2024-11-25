package org.clientsms.controller;

import lombok.RequiredArgsConstructor;
import org.clientsms.config.ApplicationPropertiesConfiguration;
import org.clientsms.model.Client;
import org.clientsms.service.ClientService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@RefreshScope
public class ClientController {

    private final JobLauncher jobLauncher;

    private final Job rubJob;

    private final ClientService clientService;

    private final ApplicationPropertiesConfiguration propertiesConfiguration;

    @GetMapping
    public ResponseEntity<List<Client>> getPaginatedClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
       return ResponseEntity.ok(
               clientService.getClients(page,size).stream().toList().subList(0,
               propertiesConfiguration.getLimitDeProduits()));
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @GetMapping("/ok")
    public void insert() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(rubJob, jobParameters);
            System.out.println("Import Job executed at " + new java.util.Date());
        } catch (Exception e) {
            System.err.println("Error occurred while executing the job:");
            e.printStackTrace();
        }
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<?> getClient(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok().body(clientService.getClient(clientId));
    }

}
