package ru.mts.process_service.bpmn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.data_models.OptyData;
import ru.mts.process_service.service.KafkaConsumerService;

@Component
public class SendToValidate implements JavaDelegate {
    @Autowired
    private KafkaTemplate<String, OptyData> kafkaTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String optyJson = delegateExecution.getVariable("optyData").toString();
        OptyData optyData = objectMapper.readValue(optyJson, OptyData.class);
        kafkaTemplate.send("client_data_validator_send", optyData.getId(), optyData);
    }
}
