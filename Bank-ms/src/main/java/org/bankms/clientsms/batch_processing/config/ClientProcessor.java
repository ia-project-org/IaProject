package org.bankms.clientsms.batch_processing.config;


import org.bankms.clientsms.model.Client;
import org.springframework.batch.item.ItemProcessor;

public class ClientProcessor implements ItemProcessor<Client, Client> {

    @Override
    public Client process(Client item) throws Exception {
        // logic metier .....
        return item;
    }
}
