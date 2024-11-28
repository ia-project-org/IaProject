package org.eligibilityms.repository;

import org.eligibilityms.model.EligibilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityStatusRepository extends JpaRepository<EligibilityStatus,Long> {

    EligibilityStatus findEligibilityStatusByClientId(Long clientId);

}
