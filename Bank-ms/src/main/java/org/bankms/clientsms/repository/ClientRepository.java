package org.bankms.clientsms.repository;

import org.bankms.clientsms.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findClientByClientId(Long clientId);

    boolean existsByCinAndEmail(String cin,String emil);

}
