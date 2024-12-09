package org.eligibilityms.repository;

import org.eligibilityms.model.EligibilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EligibilityStatusRepository extends JpaRepository<EligibilityStatus,Long> {

    @Query("SELECT e FROM EligibilityStatus e WHERE e.clientId = :clientId ORDER BY e.lastCheckedDate DESC LIMIT 1")
    EligibilityStatus findLatestEligibilityStatusByClientId(@Param("clientId") Long clientId);

    @Query("SELECT count(e) FROM EligibilityStatus e WHERE e.eligibilityResult = :eligibilityResult")
    Integer countByEligibilityResult(@Param("eligibilityResult") String eligibilityResult);

    @Query("SELECT count(e) FROM EligibilityStatus e WHERE e.eligibilityResult = :eligibilityResult AND e.lastCheckedDate <= :lastMonth")
    Integer countByEligibilityResultBeforeLastMonth(@Param("eligibilityResult") String eligibilityResult, @Param("lastMonth") Date lastMonth);
}
