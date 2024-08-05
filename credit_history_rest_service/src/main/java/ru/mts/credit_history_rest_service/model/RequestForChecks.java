package ru.mts.credit_history_rest_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "requests_for_checks")
@Data
public class RequestForChecks {
    @Id
    @Column(name = "opty_id")
    private String optyId;
    private String status;
    private String created;
    @Column(name = "date_decision")
    private String dateDecision;
}