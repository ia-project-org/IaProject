package org.bankms.clientsms.repository;

import org.bankms.clientsms.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findClientByClientId(Long clientId);

    long countByCreatedAtLessThanEqual(LocalDateTime endDateTime);
}
