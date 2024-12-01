package org.eligibilityms.controller;

import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import org.eligibilityms.proxy.BankMsProxy;
import org.eligibilityms.proxy.IaModelMsProxy;
import org.eligibilityms.service.EligibilityStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eligibility")
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
    public ResponseEntity<?> evaluateClientEligibility(@PathVariable("clientId") Long clientId) {

        String creditScore = JsonPath.parse(
                        iaModelMsProxy.evaluateClientEligibility(
                                clientsMsProxy.getClientDetails(clientId)).getBody())
                .read("$.credit_score");
        eligibilityStatusService.saveClientStatus(creditScore, clientId);
        return ResponseEntity.ok().body(creditScore);
    }
}
