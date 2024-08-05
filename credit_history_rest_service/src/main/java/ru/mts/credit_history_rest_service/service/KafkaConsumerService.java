package ru.mts.credit_history_rest_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mts.credit_history_rest_service.model.RequestForChecks;
import ru.mts.credit_history_rest_service.repository.RequestsRepository;
import ru.mts.data_models.OptyData;
import ru.mts.data_models.StatusData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaConsumerService {
    @Autowired
    private RequestsRepository requestsRepository;

    @KafkaListener(topics = "check_history_send", groupId = "process-group5")
    public void listen(StatusData statusData) {
        RequestForChecks requestsForChecks = new RequestForChecks();
        requestsForChecks.setOptyId(statusData.getId());
        requestsForChecks.setStatus("NEW");
        requestsForChecks.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        requestsRepository.save(requestsForChecks);
    }
}