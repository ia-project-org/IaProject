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

    @Override
    public EligibilityStatus updateClientStatus(String result, Long clientId) {
        return eligibilityStatusRepository.save(
                EligibilityStatus.builder()
                        .eligibilityResult(result)
                        .lastCheckedDate(new Date())
                        .clientId(clientId)
                        .build()
        );
    }

    @Override
    public EligibilityStatus getClientStatus(Long clientId) {
        return eligibilityStatusRepository.findEligibilityStatusByClientId(clientId);
    }
}
