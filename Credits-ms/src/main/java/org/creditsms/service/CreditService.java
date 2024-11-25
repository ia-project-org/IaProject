package org.creditsms.service;

import org.creditsms.model.Credit;
import org.springframework.data.domain.Page;

public interface CreditService {

    Page<Credit> getCredits(int page,int size);

    Credit saveCredit(Credit credit);

}
