package ru.mts.opty_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "opty")
@Data
public class Opty {
    @Id
    private String id;
    private String status;
    private String created;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "Id")
    private OptyClient client;
}