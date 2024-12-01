package org.bankms.batch_processing.creditsconfig;


import org.bankms.creditsms.model.Credit;
import org.springframework.batch.item.ItemProcessor;

public class CreditProcessor implements ItemProcessor<Credit,Credit> {

    @Override
    public Credit process(Credit item) throws Exception {
        return item;
    }
}
