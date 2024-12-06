package org.eligibilityms.controller;

import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.eligibilityms.proxy.BankMsProxy;
import org.eligibilityms.proxy.IaModelMsProxy;
import org.eligibilityms.service.EligibilityStatusService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eligibility")
@CrossOrigin("http://localhost:5173/")
public class EligibilityController {

    private final BankMsProxy clientsMsProxy;

    private final IaModelMsProxy iaModelMsProxy;

    private final EligibilityStatusService eligibilityStatusService;

    /**
     * get the eligibility status for a single client
     *
     * @param clientId
     * @return
     */
    @GetMapping("/{clientId}")
    @PreAuthorize("hasRole('agent')")
    public ResponseEntity<?> getClientEligibilityStatus(@PathVariable("clientId") Long clientId) {
        return ResponseEntity.ok().body(eligibilityStatusService.getClientEligibilityStatus(clientId));
    }

    /**
     * evaluate client eligibility and save the result in the database
     *
     * @param clientId
     * @return
     */
    @PostMapping("/{clientId}")
    @PreAuthorize("hasRole('agent')")
    public ResponseEntity<?> evaluateClientEligibility(@PathVariable("clientId") Long clientId) {

        String creditScore = JsonPath.parse(
                        iaModelMsProxy.evaluateClientEligibility(
                                clientsMsProxy.getClientDetails(clientId)).getBody())
                .read("$.credit_score");
        return ResponseEntity.ok().body(eligibilityStatusService.saveClientEligibilityStatus(creditScore, clientId));
    }
}
