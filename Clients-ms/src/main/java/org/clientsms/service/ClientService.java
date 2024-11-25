package org.clientsms.service;

import org.clientsms.model.Client;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);

    Page<Client> getClients(int page, int size);

    Optional<Client> getClient(Long clientId);

}
