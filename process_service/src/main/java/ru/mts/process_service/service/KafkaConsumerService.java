package ru.mts.process_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mts.data_models.OptyData;
import ru.mts.data_models.StatusData;
import ru.mts.process_service.helper.TextMessages;

import java.util.Map;
import java.util.UUID;

@Log4j2
@Service
public class KafkaConsumerService {
    @Autowired
    private RuntimeService runtimeService;

    @KafkaListener(topics = "process_service", groupId = "process-group1")
    public void listen(OptyData opty) throws JsonProcessingException {
        log.info("Запускается процесс камунды: " + opty.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String optyJson = objectMapper.writeValueAsString(opty);
        runtimeService.startProcessInstanceByKey("simple", opty.getId(),
                Map.of("optyId", opty.getId(),
                        "status", "NEW",
                        "comment", TextMessages.REGISTER_OPTY.getMessage(),
                        "optyData", optyJson));
        log.info("Зарегестрирована заявка с Id: " + opty.getId());
    }

    @KafkaListener(topics = "client_data_validator_receive", groupId = "process-group3")
    public void listen2(StatusData statusData) {
        log.info("Получено сообщение из сервиса валидации: " + statusData.getId());
        runtimeService.correlateMessage("VALIDATE_OVER", statusData.getId(),
                Map.of("status", statusData.getStatus(),
                        "comment", statusData.getComment()));
    }

    @KafkaListener(topics = "check_history_receive", groupId = "process-group6")
    public void listen3(StatusData statusData) {
        log.info("Получено сообщение из сервиса кредитной истории для заявки: " + statusData.getId());;
        runtimeService.correlateMessage("CREDIT_CHECK_OVER", statusData.getId(),
                Map.of("status", statusData.getStatus(),
                        "comment", statusData.getComment()));
    }
}