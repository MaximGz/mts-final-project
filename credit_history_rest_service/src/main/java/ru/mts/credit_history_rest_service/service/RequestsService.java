package ru.mts.credit_history_rest_service.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mts.credit_history_rest_service.model.RequestForChecks;
import ru.mts.credit_history_rest_service.repository.RequestsRepository;
import ru.mts.data_models.StatusData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class RequestsService {
    @Autowired
    private RequestsRepository requestsRepository;
    @Autowired
    private KafkaTemplate<String, StatusData> kafkaTemplate;

    @Transactional
    public void updateRequest(String optyId, String status, String comment) throws InterruptedException {
        Optional<RequestForChecks> requestOptional = requestsRepository.findById(optyId);

        if (requestOptional.isPresent()) {
            RequestForChecks request = requestOptional.get();
            request.setOptyId(optyId);
            request.setStatus(status);
            request.setDateDecision(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
            requestsRepository.save(request);

            System.out.println("Статус для запроса с Id: " + optyId + " обновлен на " + status);
        } else {
            System.out.println("Запрос с Id " + optyId + " не найден.");
        }

        StatusData statusData = new StatusData();
        statusData.setId(optyId);
        statusData.setStatus(status);
        statusData.setComment(comment);
        Thread.sleep(10000);
        kafkaTemplate.send("check_history_receive", optyId, statusData);
    }
}