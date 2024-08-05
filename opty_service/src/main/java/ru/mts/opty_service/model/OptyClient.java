package ru.mts.opty_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "opty_clients")
@Data
public class OptyClient {
    @Id
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "birth_date")
    private String birthDate;
    private String gender;
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "omt")
    private String OMT;
    @Column(name = "passport_seria")
    private String passportSeria;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "passport_type")
    private String passportType;
    private String nationality;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Opty> opties;
}
