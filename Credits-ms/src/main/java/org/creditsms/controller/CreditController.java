package org.creditsms.controller;

import lombok.RequiredArgsConstructor;
import org.creditsms.model.Credit;
import org.creditsms.service.CreditService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    private final JobLauncher jobLauncher;

    private final Job rubJob;

    @GetMapping
    public ResponseEntity<Page<Credit>> getcredits(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size){
        try{
            jobLauncher.run(rubJob,new JobParameters());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(creditService.getCredits(page, size));
    }


    @GetMapping("/o")
    public ResponseEntity<?> o(){
        try{
            jobLauncher.run(rubJob,new JobParameters());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("");
    }
    @PostMapping
    public Credit addCredit(@RequestBody Credit credit){
        return creditService.saveCredit(credit);
    }
}
