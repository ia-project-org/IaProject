package org.bankms.clientsms.controller;

import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.config.ApplicationPropertiesConfiguration;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.service.ClientService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

import static org.bankms.batch_processing.clientsconfig.ClientsImportBatchConfig.file_path;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class ClientController {

    private final JobLauncher jobLauncher;

    private final Job importClientJob;

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
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(clientService.getClients(page,size).stream());
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

    @PostMapping("/import")
    public ResponseEntity<?> importClients() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(importClientJob,new JobParametersBuilder()
                .addString("timestamp", String.valueOf(System.currentTimeMillis())) // Unique parameter
                .toJobParameters());
        return ResponseEntity.ok().body("imported successfully");
    }

    @PostMapping("/import-csv")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) throws Exception {
        file.transferTo(new File(file_path));
        jobLauncher.run(importClientJob, new JobParametersBuilder()
                        .addString("fileName", file.getOriginalFilename())
                        .addString("timsmap", String.valueOf(System.currentTimeMillis()))
                        .toJobParameters());
        return ResponseEntity.ok("CSV imported successfully.");
    }
}
