package org.springbatch.config;

import org.springbatch.client.Client;
import org.springframework.batch.item.ItemProcessor;

public class ClientProcessor implements ItemProcessor<Client, Client> {

    @Override
    public Client process(Client client) throws Exception {
        // all the business logic
        return client;
    }
}
