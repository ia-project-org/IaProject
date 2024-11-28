package org.eligibilityms.service;

import org.eligibilityms.model.EligibilityStatus;

public interface EligibilityStatusService {

    EligibilityStatus updateClientStatus(String result,Long clientId);

    EligibilityStatus getClientStatus(Long clientId);

}
