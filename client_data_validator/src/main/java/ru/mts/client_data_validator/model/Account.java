package ru.mts.client_data_validator.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @Column(name = "id", unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "created")
    private String created;
}