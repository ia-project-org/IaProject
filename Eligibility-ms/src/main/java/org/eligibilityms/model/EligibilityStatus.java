package org.eligibilityms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EligibilityStatus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eligibilityId;
    private Date lastCheckedDate;
    private String eligibilityResult;

    public EligibilityStatus() {

    }
}
