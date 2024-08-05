package ru.mts.client_data_validator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @Column(name="id", unique = true)
    private String id;
    @Column(name = "passport_type")
    private String passportType;
    @Column(name = "passport_seria")
    private String passportSeria;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "birth_date")
    private String birthDate;
    private String gender;
    @Column(name="marital_status")
    private String maritalStatus;
    @Column(name="omt")
    private String OMT;
    private String nationality;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();
}
