package org.eligibilityms.service;

import org.eligibilityms.model.EligibilityStatus;
import org.springframework.http.ResponseEntity;

public interface EligibilityStatusService {

    /**
     * save client eligibility result in the database
     * @param clientId
     * @return
     */
    EligibilityStatus saveClientEligibilityStatus(String result, Long clientId);

    /**
     * get the eligibility status for a single client
     * @param clientId
     * @return
     */
    EligibilityStatus getClientEligibilityStatus(Long clientId);

    ResponseEntity<EligibilityStatus> evaluateClientEligibility(Long clientId);

}
