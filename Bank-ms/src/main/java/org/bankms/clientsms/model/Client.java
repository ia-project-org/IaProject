package org.bankms.clientsms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @Column(unique = true)
    private String cin;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonManagedReference
    private ClientDetails details;

    public Client() {

    }
}
