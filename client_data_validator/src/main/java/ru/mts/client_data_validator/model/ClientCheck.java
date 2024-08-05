package ru.mts.client_data_validator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "clients_checks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCheck {
    @Id
    @Column(name = "opty_id")
    private String optyId;
    @Column(name = "passport_seria")
    private Boolean passportSeria;
    @Column(name = "passport_number")
    private Boolean passportNumber;
    @Column(name = "first_name")
    private Boolean firstName;
    @Column(name = "last_name")
    private Boolean lastName;
    @Column(name = "middle_name")
    private Boolean middleName;
    @Column(name = "birth_date")
    private Boolean birthDate;
    private Boolean gender;
    @Column(name = "marital_status")
    private Boolean maritalStatus;
    @Column(name = "omt")
    private Boolean OMT;
    @Column(name = "passport_type")
    private Boolean passportType;
    private Boolean nationality;
    private String created;
}
