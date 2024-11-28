package org.bankms.clientsms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private Long clientDetailsId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;



    public Client() {

    }
}
