package ru.mts.data_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptyData {
    private String id;
    private String status;
    private ClientData clientData;
}