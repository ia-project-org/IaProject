package org.eligibilityms.service;

import lombok.RequiredArgsConstructor;
import org.eligibilityms.model.EligibilityStatus;
import org.eligibilityms.repository.EligibilityStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EligibilityStatusServiceImpl implements EligibilityStatusService{

    private final EligibilityStatusRepository eligibilityStatusRepository;

    /**
     * save client eligibility result in the database
     * @param result
     * @param clientId
     * @return
     */
    @Override
    public EligibilityStatus saveClientStatus(String result, Long clientId) {
        return eligibilityStatusRepository.save(
                EligibilityStatus.builder()
                        .eligibilityResult(result)
                        .lastCheckedDate(new Date())
                        .clientId(clientId)
                        .build()
        );
    }

    /**
     * get the eligibility status for a single client
     * @param clientId
     * @return
     */
    @Override
    public EligibilityStatus getClientEligibilityStatus(Long clientId) {
        return eligibilityStatusRepository.findEligibilityStatusByClientId(clientId);
    }
}
