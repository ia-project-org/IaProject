package org.bankms.clientsms.service;

import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.dto.ClientDto;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.repository.ClientDetailsRepository;
import org.bankms.clientsms.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    private final ClientDetailsRepository clientDetailsRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Page<Client> getClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<ClientDetails> getClientsDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientDetailsRepository.findAll(pageable);
    }

    @Override
    public Optional<Client> getClient(Long clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public Client updateClient(ClientDto clientDto,Long clientId) {
        Client client = clientRepository.findClientByClientId(clientId);
        if (client != null)
            clientRepository.save(client);
        return client;
    }

    @Override
    public ClientDetails updateClientDetails(ClientDetails clientDetails,Long clientId) {
        ClientDetails clientDetails1 = clientDetailsRepository.findClientDetailsByClientId(clientId);
        return clientDetailsRepository.save(clientDetails);
    }

    @Override
    public ClientDetails saveClientDetails(ClientDetails clientDetails) {
        return clientDetailsRepository.save(clientDetails);
    }

    @Override
    public ClientDetails getClientDetails(Long clientId) {
        return clientDetailsRepository.findClientDetailsByClientId(clientId);
    }

    @Override
    public long getNumberOfClients() {
        return clientRepository.count();
    }

    @Override
    public long getNumberOfClients(LocalDateTime endDateTime) {
        return clientRepository.countByCreatedAtLessThanEqual(endDateTime);
    }
}
