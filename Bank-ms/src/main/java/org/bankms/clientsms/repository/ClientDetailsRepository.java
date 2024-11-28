package org.bankms.clientsms.repository;

import org.bankms.clientsms.model.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails,Long> {

    ClientDetails findClientDetailsByClientId(Long clientId);

}
