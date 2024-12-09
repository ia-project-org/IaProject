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
    public EligibilityStatus saveClientEligibilityStatus(String result, Long clientId) {
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
        return eligibilityStatusRepository.findLatestEligibilityStatusByClientId(clientId);
    }

    @Override
    public Integer countByEligibilityResult(String eligibilityResult) {
        return eligibilityStatusRepository.countByEligibilityResult(eligibilityResult);
    }

    @Override
    public Integer countByEligibilityResult(String eligibilityResult, Date lastMonthDate) {
        return eligibilityStatusRepository.countByEligibilityResultBeforeLastMonth(eligibilityResult, lastMonthDate);
    }
}
