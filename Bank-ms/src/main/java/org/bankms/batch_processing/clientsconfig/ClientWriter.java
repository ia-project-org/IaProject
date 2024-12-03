package org.bankms.batch_processing.clientsconfig;

import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.repository.ClientDetailsRepository;
import org.bankms.clientsms.repository.ClientRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

public class ClientWriter implements ItemWriter<Client> {
    private final ClientRepository clientRepository;
    private final ClientDetailsRepository clientDetailsRepository;

    public ClientWriter(ClientRepository clientRepository,
                        ClientDetailsRepository clientDetailsRepository) {
        this.clientRepository = clientRepository;
        this.clientDetailsRepository = clientDetailsRepository;
    }


    @Override
    public void write(Chunk<? extends Client> clients) throws Exception {
        // Extracting the ClientDetails from the Client entities
        List<ClientDetails> clientDetailsList = new ArrayList<>();
        for (Client client : clients) {
            ClientDetails clientDetails = client.getDetails();
            clientDetailsList.add(clientDetails);
        }
        // Save ClientDetails first
        clientDetailsRepository.saveAll(clientDetailsList);
        // Save the Client entities
        clientRepository.saveAll(clients);
    }
}