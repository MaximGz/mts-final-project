package ru.mts.data_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String gender;
    private String maritalStatus;
    private String OMT;
    private String passportSeria;
    private String passportNumber;
    private String passportType;
    private String nationality;
}