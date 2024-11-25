package org.clientsms.service;

import lombok.RequiredArgsConstructor;
import org.clientsms.model.Client;
import org.clientsms.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Page<Client> getClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients;
    }

    @Override
    public Optional<Client> getClient(Long clientId) {
        return clientRepository.findById(clientId);
    }
}
